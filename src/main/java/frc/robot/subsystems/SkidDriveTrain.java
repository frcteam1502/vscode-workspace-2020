/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.ArrayList;
import java.util.Collections;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.ADXL345_I2C;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import frc.robot.Constants.Joysticks;

public class SkidDriveTrain extends SubsystemBase {
  /**
   * Creates a new SkidDrivetrain.
   */

  ArrayList<CANSparkMax> leftMotors = new ArrayList<>();
  ArrayList<CANSparkMax> rightMotors = new ArrayList<>();
  ArrayList<CANSparkMax> motors = new ArrayList<>();
  Joystick left, right;

  ADXL345_I2C accelerometer;

  private final double minMovement = 0.05;
  CANSparkMax motor;

  public SkidDriveTrain(Joystick left, Joystick right, CANSparkMax... motors) {
    Collections.addAll(this.motors, motors);
    leftMotors = (ArrayList<CANSparkMax>) this.motors.subList(0, (this.motors.size() - 1) / 2);
    rightMotors = (ArrayList<CANSparkMax>) this.motors.subList((this.motors.size() - 1) / 2, this.motors.size() - 1);
  }

  public void move() {
    double moveSpeed = left.getY();
    double rotateSpeed = right.getX();
    double leftPwr = -moveSpeed + rotateSpeed;
    double rightPwr = moveSpeed + rotateSpeed;
    if ((leftPwr > 1 || leftPwr < -1) || (rightPwr > 1 || rightPwr < -1)) {
      double max = Math.abs(Math.abs(leftPwr) > Math.abs(rightPwr) ? leftPwr : rightPwr);
      leftPwr = leftPwr / max;
      rightPwr = rightPwr / max;
    }
    final double fRightPower = rightPwr;
    final double fLeftPower = leftPwr;
    rightMotors.forEach(x -> x.set(fRightPower));
    leftMotors.forEach(x -> x.set(fLeftPower));
  }

  public boolean isSkidding() {
    return accelerometer.getZ() < motor.getEncoder().getVelocity() - minMovement
        || accelerometer.getZ() > motor.getEncoder().getVelocity() + minMovement;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    move();
  }
}
