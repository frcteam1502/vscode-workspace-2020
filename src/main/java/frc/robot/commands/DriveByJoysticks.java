package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;

public class DriveByJoysticks extends CommandBase {

  private final Drivetrain drivetrain;

  public DriveByJoysticks(Drivetrain subsystem) {
    addRequirements(subsystem);
    this.drivetrain = subsystem;
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    double moveSpeed = Constants.Joysticks.RIGHT_JOYSTICK.getY();
    double rotateSpeed = Constants.Joysticks.LEFT_JOYSTICK.getX();
    double leftPower = moveSpeed + rotateSpeed;
    double rightPower = moveSpeed - rotateSpeed;
    SmartDashboard.putNumber("Left pwr", leftPower);
    SmartDashboard.putNumber("right pwr", rightPower);
    if ((leftPower > 1 || leftPower < -1) || (rightPower > 1 || rightPower < -1)) {
      double max = Math.abs(Math.abs(leftPower) > Math.abs(rightPower) ? leftPower : rightPower);
      leftPower = leftPower / max;
      rightPower = rightPower / max;
    }
    drivetrain.move(leftPower, rightPower);
  }

  @Override
  public void end(boolean interrupted) {
    drivetrain.move(0, 0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
