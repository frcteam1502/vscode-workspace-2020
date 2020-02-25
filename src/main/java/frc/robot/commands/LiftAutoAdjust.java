package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.PIDController;
import frc.robot.subsystems.LiftAdjust;

public class LiftAutoAdjust extends CommandBase {
  private LiftAdjust subsystem;
  private PIDController pid = new PIDController(0, 0, 0);

  public LiftAutoAdjust(LiftAdjust subsystem) {
    addRequirements(subsystem);
    this.subsystem = subsystem;
  }

  @Override
  public void initialize() {

  }

  @Override
  public void execute() {
    subsystem.adjust(pid.getCorrection(subsystem.getAngle()));
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}