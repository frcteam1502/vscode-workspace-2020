/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.Joysticks.*;
import edu.wpi.first.wpilibj.ADXL345_I2C;

public class SkidDrivetrain extends SubsystemBase {
  private final ADXL345_I2C accelerometer;
  private final CANSparkMax FRONT_LEFT,  BACK_LEFT,  FRONT_RIGHT,  BACK_RIGHT;

  private final double minMovement = 0.05;
  CANSparkMax motor;

  public SkidDrivetrain(ADXL345_I2C accelerometer, CANSparkMax FRONT_LEFT, CANSparkMax BACK_LEFT, CANSparkMax FRONT_RIGHT, CANSparkMax BACK_RIGHT) {
    this.FRONT_LEFT = FRONT_LEFT;
    this.BACK_LEFT = BACK_LEFT;
    this.FRONT_RIGHT = FRONT_RIGHT;
    this.BACK_RIGHT = BACK_RIGHT;
    this.accelerometer = accelerometer;
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
