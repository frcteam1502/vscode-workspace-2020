/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import static frc.robot.Constants.Joysticks.*;
import edu.wpi.first.wpilibj.ADXL345_I2C;
import edu.wpi.first.wpilibj.ADXL345_I2C.Axes;
import edu.wpi.first.wpilibj.Timer;

public class SkidDriveTrain extends SubsystemBase {
  private final ADXL345_I2C accelerometer;
  private final AHRS navx;
  private final CANSparkMax FRONT_LEFT, BACK_LEFT, FRONT_RIGHT, BACK_RIGHT;

  private final double minMovement = 0.05;
  private static final double UNITCONVERT = 6 * Math.PI / 10.9 * 2.54;
  private final double SKIDRATE = 2;
  private final double ENCODERTICKS = .5;
  CANSparkMax motor;
  Boolean toggle = true;

  public SkidDriveTrain(ADXL345_I2C accelerometer, CANSparkMax FRONT_LEFT, CANSparkMax BACK_LEFT,
      CANSparkMax FRONT_RIGHT, CANSparkMax BACK_RIGHT, AHRS navx) {
    this.FRONT_LEFT = FRONT_LEFT;
    this.BACK_LEFT = BACK_LEFT;
    this.FRONT_RIGHT = FRONT_RIGHT;
    this.BACK_RIGHT = BACK_RIGHT;
    this.accelerometer = accelerometer;
    this.navx = navx;
  }

  public void move() {
    double moveSpeed = LEFT_JOYSTICK.getY() > .1 ? Math.pow(LEFT_JOYSTICK.getY(), 3) : 0;
    double rotateSpeed = RIGHT_JOYSTICK.getX() > .1 ? Math.pow(RIGHT_JOYSTICK.getX(), 3) : 0;
    double leftPwr = -moveSpeed + rotateSpeed;
    double rightPwr = moveSpeed + rotateSpeed;
    if ((leftPwr > 1 || leftPwr < -1) || (rightPwr > 1 || rightPwr < -1)) {
      double max = Math.abs(Math.abs(leftPwr) > Math.abs(rightPwr) ? leftPwr : rightPwr);
      leftPwr = leftPwr / max;
      rightPwr = rightPwr / max;
    }
    FRONT_LEFT.set(leftPwr);
    BACK_LEFT.set(leftPwr);
    FRONT_RIGHT.set(rightPwr);
    BACK_RIGHT.set(rightPwr);

    if (isSkidding()) {
      skidControl();
      toggle = !toggle;
    }
  }

  /*
   * get position target position if position != target move
   */
  public void skidControl() {
    if (toggle) {
      if (FRONT_LEFT.getEncoder().getPosition() < FRONT_LEFT.getEncoder().getPosition() + 5
          || FRONT_RIGHT.getEncoder().getPosition() < FRONT_RIGHT.getEncoder().getPosition() + 5) {
        FRONT_LEFT.setOpenLoopRampRate(SKIDRATE);
        BACK_LEFT.setOpenLoopRampRate(SKIDRATE);
        FRONT_RIGHT.setOpenLoopRampRate(SKIDRATE);
        BACK_RIGHT.setOpenLoopRampRate(SKIDRATE);
      }
    } else {
      if (FRONT_LEFT.getEncoder().getPosition() < FRONT_LEFT.getEncoder().getPosition() - 5
          || FRONT_RIGHT.getEncoder().getPosition() < FRONT_RIGHT.getEncoder().getPosition() - 5) {
        FRONT_LEFT.setOpenLoopRampRate(-SKIDRATE);
        BACK_LEFT.setOpenLoopRampRate(-SKIDRATE);
        FRONT_RIGHT.setOpenLoopRampRate(-SKIDRATE);
        BACK_RIGHT.setOpenLoopRampRate(-SKIDRATE);
      }

    }
  }

  public boolean isSkidding() {
    double moveSpeed = LEFT_JOYSTICK.getY();
    double forwardVelocity = Math.sqrt(Math.pow(navx.getVelocityX(), 2) + Math.pow(navx.getVelocityZ(), 2))
        * Math.abs(moveSpeed) / moveSpeed;

    return motor.getEncoder().getVelocity() * UNITCONVERT <= forwardVelocity + 20
        || motor.getEncoder().getVelocity() * UNITCONVERT >= forwardVelocity - 20;
  }

  /*
   * double realVelocity = accelerometer.getZ(); double motorVelocitty =
   * motor.getEncoder().getVelocity(); return realVelocity < motorVelocitty -
   * minMovement || realVelocity > motorVelocitty + minMovement;
   */

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    move();
  }
}
