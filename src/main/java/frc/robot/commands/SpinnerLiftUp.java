package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SpinnerLift;

public class SpinnerLiftUp extends CommandBase {
  SpinnerLift subsystem;

  public SpinnerLiftUp(SpinnerLift subsystem) {
    addRequirements(subsystem);
    this.subsystem = subsystem;
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    subsystem.setLift(.5);
  }

  @Override
  public void end(boolean interrupted) {
    subsystem.setLift(0);
  }

  @Override
  public boolean isFinished() {
    return subsystem.getUpperLimit();
  }
}
