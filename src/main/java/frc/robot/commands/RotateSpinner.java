package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Spinner;

public class RotateSpinner extends CommandBase {

  Spinner subsystem;

  public RotateSpinner(Spinner subsystem) {
    addRequirements(subsystem);
    this.subsystem = subsystem;
  }

  @Override
  public void initialize() {
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
    return !subsystem.checkSpinner();
  }
}
