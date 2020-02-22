package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Spinner;

public class MoveTo extends CommandBase {
  private final Spinner subsystem;

  public MoveTo(Spinner subsystem) {
    this.subsystem = subsystem;
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    if (isFinished())
      end(false);
    else
      subsystem.setSpeed(.05);
  }

  @Override
  public void end(boolean interrupted) {
    subsystem.setSpeed(0);
  }

  @Override
  public boolean isFinished() {
    String gameData = DriverStation.getInstance().getGameSpecificMessage();
    return gameData != null && subsystem.checkSpinner(gameData);
  }
}
