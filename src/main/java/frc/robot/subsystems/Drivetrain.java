/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Joysticks;
import frc.robot.commands.DriveCommand;

public class Drivetrain extends SubsystemBase {

  static final double INCHES_PER_ENCODER_TICK = 89.0 / 50.0;
  CANSparkMax LEFT_FRONT, LEFT_BACK, RIGHT_FRONT, RIGHT_BACK;

  public Drivetrain(CANSparkMax LEFT_FRONT, CANSparkMax LEFT_BACK, CANSparkMax RIGHT_FRONT, CANSparkMax RIGHT_BACK) {
    setDefaultCommand(new DriveCommand(this));
    this.LEFT_FRONT = LEFT_FRONT;
    this.LEFT_BACK = LEFT_BACK;
    this.RIGHT_FRONT = RIGHT_FRONT;
    this.RIGHT_BACK = RIGHT_BACK;
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
    SmartDashboard.putNumber("Left power", leftPwr);
    SmartDashboard.putNumber("Right power", rightPwr);
    SmartDashboard.putNumber("Move speed", moveSpeed);
    SmartDashboard.putNumber("Rotate speed", rotateSpeed);
    move(leftPwr, rightPwr);
    /*
     * double moveSpeed = Joysticks.RIGHT_JOYSTICK.getY() > .1 ||
     * Joysticks.RIGHT_JOYSTICK.getY() < -.1 ? Joysticks.RIGHT_JOYSTICK.getY() : 0;
     * double rotateSpeed = Joysticks.LEFT_JOYSTICK.getX() > .1 ||
     * Joysticks.LEFT_JOYSTICK.getX() < -.1 ? Joysticks.LEFT_JOYSTICK.getX() : 0;
     * moveSpeed = Math.pow(moveSpeed, 3); rotateSpeed = Math.pow(rotateSpeed, 3);
     * double leftPwr = -moveSpeed + rotateSpeed; double rightPwr = moveSpeed +
     * rotateSpeed; if ((leftPwr > 1 || leftPwr < -1) || (rightPwr > 1 || rightPwr <
     * -1)) { double max = Math.abs(Math.abs(leftPwr) > Math.abs(rightPwr) ? leftPwr
     * : rightPwr); leftPwr = leftPwr / max; rightPwr = rightPwr / max; } if
     * (lidar.getCM() < 400 && Buttons.RIGHT_TRIGGER.get()) { leftPwr = rightPwr =
     * 0; } SmartDashboard.putNumber("Right power", rightPwr);
     * SmartDashboard.putNumber("Left power", leftPwr); frontRight.set(rightPwr);
     * backRight.set(rightPwr); frontLeft.set(leftPwr); backLeft.set(leftPwr);
     */
  }

  public void move(double leftPower, double rightPower) {
    LEFT_FRONT.set(leftPower);
    LEFT_BACK.set(leftPower);
    RIGHT_FRONT.set(-rightPower);
    RIGHT_BACK.set(-rightPower);
  }

  public void resetEncoders() {
    LEFT_FRONT.getEncoder().setPosition(0);
    LEFT_BACK.getEncoder().setPosition(0);
    RIGHT_FRONT.getEncoder().setPosition(0);
    RIGHT_BACK.getEncoder().setPosition(0);
  }

  public double getLeftEncoderPosition() {
    return (LEFT_FRONT.getEncoder().getPosition() + LEFT_BACK.getEncoder().getPosition()) / 2;
  }

  public double getRightEncoderPosition() {
    return -(RIGHT_FRONT.getEncoder().getPosition() + RIGHT_BACK.getEncoder().getPosition()) / 2;
  }

  /**
   * Returns the average of the left and right encoder positions. Remember to
   * reset both encoders to zero some time before using in order to make the
   * average value relevant.
   */
  public double getAverageEncoderPosition() {
    return (getLeftEncoderPosition() + getRightEncoderPosition()) / 2;
  }

  @Override
  public void periodic() {
  }
}
