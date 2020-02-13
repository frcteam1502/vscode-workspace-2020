/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import static frc.robot.Constants.Joysticks.LEFT_JOYSTICK;
import static frc.robot.Constants.Joysticks.RIGHT_JOYSTICK;

import java.util.function.Function;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.PIDController;
import frc.robot.commands.IntegratedDrivetrainCommand;

public class IntegratedDrivetrain extends SubsystemBase {

  private static final double STOP_TIME = 2;
  private static final double INCHES_PER_ENCODER_VALUE = 89.0 / 50.0;
  private final LidarSubsystem lidarSubsystem;
  private final PIDController lidarStopController = new PIDController(2e-8, 0, 0);
  private final CANSparkMax frontLeft, backLeft, frontRight, backRight;
  private Double stopDistance = null;
  private boolean direction = true; // forward is true because it is default
  double farVal = 1;
  double targetDistance = 150;
  double startDistance = 200;

  public IntegratedDrivetrain(LidarSubsystem lidarSubsystem, CANSparkMax frontLeft, CANSparkMax backLeft,
      CANSparkMax frontRight, CANSparkMax backRight) {
    setDefaultCommand(new IntegratedDrivetrainCommand(this));
    this.lidarSubsystem = lidarSubsystem;
    this.frontLeft = frontLeft;
    this.backLeft = backLeft;
    this.frontRight = frontRight;
    this.backRight = backRight;
  }

  private boolean hasReachedStoppingDistance() {
    double averageVel = (getLeftEncoderVelocity() + getRightEncoderVelocity()) / 2;
    double speedInCmPerSecond = averageVel * Constants.ConversionFactors.CENTIMETERS_PER_SECOND_PER_ENCODER_RPM;
    SmartDashboard.putNumber("Vel CM", speedInCmPerSecond);
    SmartDashboard.putNumber("Other thing", speedInCmPerSecond / STOP_TIME);
    if (lidarSubsystem.getCM() < speedInCmPerSecond / STOP_TIME && stopDistance == null) {
      stopDistance = lidarSubsystem.getCM();
    }

    if (stopDistance != null) {
      SmartDashboard.putNumber("Stop Distance", stopDistance);
      if (lidarSubsystem.getCM() > stopDistance) {
        SmartDashboard.putNumber("Range", 1);
        stopDistance = null;
      } else {
        SmartDashboard.putNumber("Range", 0);
        return lidarSubsystem.getCM() < stopDistance;
      }
    }
    return false;
  }

  public void move(boolean lidarOn) {
    double moveSpeed = 0;
    if (hasReachedStoppingDistance() && lidarOn) {
      lidarStopController.input(lidarSubsystem.getCM());
      moveSpeed = lidarStopController.getCorrection();
    } else
      moveSpeed = Math.pow(RIGHT_JOYSTICK.getY(), 3);
    double rotateSpeed = Math.pow(LEFT_JOYSTICK.getX(), 3);
    double leftPwr = -moveSpeed + rotateSpeed;
    double rightPwr = moveSpeed + rotateSpeed;
    if ((leftPwr > 1 || leftPwr < -1) || (rightPwr > 1 || rightPwr < -1)) {
      double max = Math.abs(Math.abs(leftPwr) > Math.abs(rightPwr) ? leftPwr : rightPwr);
      leftPwr = leftPwr / max;
      rightPwr = rightPwr / max;
    }
    frontLeft.set(leftPwr);
    backLeft.set(leftPwr);
    frontRight.set(rightPwr);
    backRight.set(rightPwr);
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

  public void reverse() {
    direction = !direction;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}