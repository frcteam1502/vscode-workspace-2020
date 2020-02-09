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
import static frc.robot.Constants.Joysticks.*;

import java.util.function.Function;

import frc.robot.Lidar;
import frc.robot.PIDController;
import frc.robot.commands.IntegratedDrivetrainCommand;

public class IntegratedDrivetrain extends SubsystemBase {

  private static final double STOP_TIME = .5;
  private static final double INCHES_PER_ENCODER_VALUE = (60 * Math.PI) / 109;
  private final Lidar LIDAR;
  private final PIDController PID;
  private final CANSparkMax FRONT_LEFT, BACK_LEFT, FRONT_RIGHT, BACK_RIGHT;
  private boolean direction = true; // forward is true because it is default

  public IntegratedDrivetrain(Lidar LIDAR, PIDController PID, CANSparkMax FRONT_LEFT, CANSparkMax BACK_LEFT,
      CANSparkMax FRONT_RIGHT, CANSparkMax BACK_RIGHT) {
    setDefaultCommand(new IntegratedDrivetrainCommand(this));
    this.LIDAR = LIDAR;
    this.PID = PID;
    this.FRONT_LEFT = FRONT_LEFT;
    this.BACK_LEFT = BACK_LEFT;
    this.FRONT_RIGHT = FRONT_RIGHT;
    this.BACK_RIGHT = BACK_RIGHT;
  }

  private boolean isClose() {
    return average(x -> x.getEncoder().getVelocity(), BACK_LEFT, FRONT_RIGHT, BACK_RIGHT) * INCHES_PER_ENCODER_VALUE
        / LIDAR.getDistance() < STOP_TIME;
  }

  private double average(Function<CANSparkMax, Double> func, CANSparkMax... motors) {
    double val = 0;
    for (CANSparkMax x : motors)
      val += func.apply(x);
    return val / motors.length;
  }

  public void move(boolean lidarOn) {
    double moveSpeed = 0;
    if (isClose() && lidarOn && direction) {
    PID.input(LIDAR.getDistance());
    moveSpeed = PID.getCorrection();
    }
    else if (!direction)
      moveSpeed = -Math.pow(RIGHT_JOYSTICK.getY(), 3);
    else
      moveSpeed = Math.pow(RIGHT_JOYSTICK.getY(), 3);
    SmartDashboard.putNumber("movespeed", moveSpeed);
    SmartDashboard.putBoolean("Forward", direction);
    double rotateSpeed = Math.pow(LEFT_JOYSTICK.getX(), 3);
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

  public void reverse() {
    direction = !direction;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}