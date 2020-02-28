package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Intake;

public class RunIntake extends CommandBase {
  private Intake subsystem;
  private double speed;

  public RunIntake(Intake subsystem, double speed) {
    addRequirements(subsystem);
    this.subsystem = subsystem;
    this.speed = speed;
  }

  @Override
  public void initialize() {
    Constants.Joysticks.XBOX.setLeftRumble(1);
  }

  @Override
  public void execute() {
    subsystem.run(speed);
  }

  @Override
  public void end(boolean interrupted) {
    subsystem.run(0);
    Constants.Joysticks.XBOX.setRightRumble(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
