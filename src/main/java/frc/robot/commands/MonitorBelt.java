package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.IntakeBelt;

public class MonitorBelt extends CommandBase {
  private IntakeBelt subsystem;

  public MonitorBelt(IntakeBelt subsystem) {
    addRequirements(subsystem);
    this.subsystem = subsystem;
  }

  @Override
  public void initialize() {
    subsystem.init();
  }

  @Override
  public void execute() {
    /**
     * Zoo Wee Momma. This was some questionable code.
     */
    SmartDashboard.putNumber("Amount of Balls", subsystem.getBalls());
    if (subsystem.isBroken())
      CommandScheduler.getInstance().schedule(new MoveBeltOneBall(subsystem));
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
