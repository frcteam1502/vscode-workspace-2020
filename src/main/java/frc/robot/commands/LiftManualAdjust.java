package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LiftAdjust;

public class LiftManualAdjust extends CommandBase {
  private LiftAdjust subsystem;
  private int speed;

  public LiftManualAdjust(LiftAdjust subsystem, int speed) {
    addRequirements(subsystem);
    this.subsystem = subsystem;
    this.speed = speed;
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    subsystem.adjust(speed);
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
