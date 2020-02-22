package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BuddyLift;

public class RetractBuddyLiftPins extends CommandBase {
  private BuddyLift subsystem;
  private double startTime;

  public RetractBuddyLiftPins(BuddyLift subsystem) {
    addRequirements(subsystem);
    this.subsystem = subsystem;
  }

  @Override
  public void initialize() {
    startTime = Timer.getFPGATimestamp();
  }

  @Override
  public void execute() {
    subsystem.setSpeed(1);
  }

  @Override
  public void end(boolean interrupted) {
    subsystem.setSpeed(0);
  }

  @Override
  public boolean isFinished() {
    return (Timer.getFPGATimestamp() <= startTime + .5);
  }
}