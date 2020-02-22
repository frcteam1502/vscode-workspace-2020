package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ConversionFactors;
import frc.robot.subsystems.Spinner;

public class MoveSpinnerByEncoder extends CommandBase {

  private Spinner subsystem;

  public MoveSpinnerByEncoder(Spinner subsystem) {
    addRequirements(subsystem);
    this.subsystem = subsystem;
  }

  @Override
  public void initialize() {
    subsystem.init();
  }

  @Override
  public void execute() {
    subsystem.setSpeed(.1);
  }

  @Override
  public void end(boolean interrupted) {
    subsystem.setSpeed(0);
  }

  @Override
  public boolean isFinished() {
    return ConversionFactors.ENCODERS_PER_SPINNER_ROTATION * 8 < subsystem.getPosition();
  }
}
