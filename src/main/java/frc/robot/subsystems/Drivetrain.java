package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.Joysticks;
import frc.robot.commands.DriveByJoysticks;

public class Drivetrain extends SubsystemBase {
  private final CANSparkMax frontLeft, backLeft, frontRight, backRight;

  public Drivetrain(CANSparkMax frontLeft, CANSparkMax backLeft, CANSparkMax frontRight, CANSparkMax backRight) {
    setDefaultCommand(new DriveByJoysticks(this));
    this.frontLeft = frontLeft;
    this.backLeft = backLeft;
    this.frontRight = frontRight;
    this.backRight = backRight;
  }

  public void move(double leftPower, double rightPower) {
    SmartDashboard.putNumber("Front", Constants.Sensors.FRONT_LIDAR.getDistance());
    SmartDashboard.putNumber("Back", Constants.Sensors.BACK_LIDAR.getDistance());
    frontLeft.set(leftPower);
    backLeft.set(leftPower);
    frontRight.set(-rightPower);
    backRight.set(-rightPower);

    SmartDashboard.putNumber("LFTemp", (frontLeft.getMotorTemperature() * 9 / 5) + 32);
    SmartDashboard.putNumber("LBTemp", (backLeft.getMotorTemperature() * 9 / 5) + 32);
    SmartDashboard.putNumber("RFTemp", (frontRight.getMotorTemperature() * 9 / 5) + 32);
    SmartDashboard.putNumber("RBTemp", (backRight.getMotorTemperature() * 9 / 5) + 32);
    SmartDashboard.putBoolean("Pressed", Joysticks.XBOX.BACK.get());
  }

  public void resetEncoders() {
    frontLeft.getEncoder().setPosition(0);
    backLeft.getEncoder().setPosition(0);
    frontLeft.getEncoder().setPosition(0);
    backRight.getEncoder().setPosition(0);
  }

  public double getLeftEncoderPosition() {
    return (frontLeft.getEncoder().getPosition() + backLeft.getEncoder().getPosition()) / 2;
  }

  public double getRightEncoderPosition() {
    return -(frontLeft.getEncoder().getPosition() + backRight.getEncoder().getPosition()) / 2;
  }

  /**
   * Returns the average of the left and right encoder positions. Remember to
   * reset both encoders to zero some time before using in order to make the
   * average value relevant.
   */
  public double getAverageEncoderPosition() {
    return (getLeftEncoderPosition() + getRightEncoderPosition()) / 2;
  }

  public double getLeftEncoderVelocity() {
    return (frontLeft.getEncoder().getVelocity() + backLeft.getEncoder().getVelocity()) / 2;
  }

  public double getRightEncoderVelocity() {
    return -(frontRight.getEncoder().getVelocity() + backRight.getEncoder().getVelocity()) / 2;
  }
}
