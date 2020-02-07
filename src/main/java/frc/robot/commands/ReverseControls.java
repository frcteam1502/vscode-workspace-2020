package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntegratedDrivetrain;

public class ReverseControls extends CommandBase {

  private final IntegratedDrivetrain subsystem;

  public ReverseControls(IntegratedDrivetrain subsystem) {
    addRequirements(subsystem);
    this.subsystem = subsystem;
  }

  @Override
  public void initialize() {
    subsystem.reverse();
  }

  @Override
  public void execute() {
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
