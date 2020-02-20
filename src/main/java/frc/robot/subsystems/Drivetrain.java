/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Joysticks;
import frc.robot.Lidar;
import frc.robot.commands.DriveByJoysticks;

public class Drivetrain extends SubsystemBase {

  final CANSparkMax frontLeft, backLeft, frontRight, backRight;
  private Lidar back;
  private Lidar front;

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
  }

  public void moveByJoysticks() {
    double moveSpeed = Math.pow(Joysticks.RIGHT_JOYSTICK.getY(), 3);
    double rotateSpeed = Math.pow(Joysticks.LEFT_JOYSTICK.getX(), 3);
    double leftPwr = -moveSpeed + rotateSpeed;
    double rightPwr = -moveSpeed - rotateSpeed;
    if (Math.abs(leftPwr) >= 1 || Math.abs(rightPwr) >= 1) {
      double max = Math.abs(Math.abs(moveSpeed) >= Math.abs(rotateSpeed) ? moveSpeed : rotateSpeed);
      leftPwr /= max;
      rightPwr /= max;
    }
    move(leftPwr, rightPwr);
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
