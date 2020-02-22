package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Lidar;
import frc.robot.commands.DriveByJoysticks;

public class Drivetrain extends SubsystemBase {
  private final CANSparkMax frontLeft, backLeft, frontRight, backRight;
  private final Lidar back;
  private final Lidar front;

  public Drivetrain(Lidar back, Lidar front, CANSparkMax frontLeft, CANSparkMax backLeft, CANSparkMax frontRight,
      CANSparkMax backRight) {
    setDefaultCommand(new DriveByJoysticks(this));
    this.frontLeft = frontLeft;
    this.backLeft = backLeft;
    this.frontRight = frontRight;
    this.backRight = backRight;
    this.back = back;
    this.front = front;
  }

  public void move(double leftPower, double rightPower) {
    frontLeft.set(leftPower);
    backLeft.set(leftPower);
    frontRight.set(-rightPower);
    backRight.set(-rightPower);
    SmartDashboard.putNumber("Back distance", back.getDistance());
    SmartDashboard.putNumber("Front distace", front.getDistance());
  }

  public double getLeftEncoderPosition() {
    return (frontLeft.getEncoder().getPosition() + backLeft.getEncoder().getPosition()) / 2;
  }

  public double getRightEncoderPosition() {
    return -(frontRight.getEncoder().getPosition() + backRight.getEncoder().getPosition()) / 2;
  }

  public double getLeftEncoderVelocity() {
    return (frontLeft.getEncoder().getVelocity() + backLeft.getEncoder().getVelocity()) / 2;
  }

  public double getRightEncoderVelocity() {
    return -(frontRight.getEncoder().getVelocity() + backRight.getEncoder().getVelocity()) / 2;
  }
}
