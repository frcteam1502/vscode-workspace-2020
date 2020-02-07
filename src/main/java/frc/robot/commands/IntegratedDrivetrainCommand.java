package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.IntegratedDrivetrain;

public class IntegratedDrivetrainCommand extends CommandBase {

  private final IntegratedDrivetrain subsystem;

  public IntegratedDrivetrainCommand(IntegratedDrivetrain subsystem) {
    addRequirements(subsystem);
    this.subsystem = subsystem;
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    subsystem.move(Constants.Buttons.LEFT_TRIGGER.get());
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
