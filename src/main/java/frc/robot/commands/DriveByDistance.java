/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.PIDController;
import frc.robot.subsystems.Drivetrain;

public class DriveByDistance extends CommandBase {

  double initialHeading;
  Drivetrain drivetrain;
  PIDController rotationController = new PIDController(1e-5, 0, 0);
  double targetEncoderPosition;

  public DriveByDistance(Drivetrain drivetrain, double targetEncoderValue) {
    this.drivetrain = drivetrain;
    this.targetEncoderPosition = targetEncoderValue;
    initialHeading = Constants.Sensors.LIFT_GYRO.getAngle();
    addRequirements(drivetrain);
  }

  @Override
  public void initialize() {
    drivetrain.resetEncoders();
  }

  @Override
  public void execute() {
    double offset = rotationController.getCorrection();
    double error = Constants.Sensors.LIFT_GYRO.getAngle() - initialHeading;
    rotationController.input(error);
    SmartDashboard.putNumber("DriveByDistance pid error", error);
    SmartDashboard.putNumber("DriveByDistance pid offset", offset);
    double averageEncoderPosition = (drivetrain.getLeftEncoderPosition() + drivetrain.getRightEncoderPosition()) / 2;
    SmartDashboard.putNumber("DriveByDistance encoder pos", averageEncoderPosition);
    SmartDashboard.putNumber("DriveByDistance encoder target", targetEncoderPosition);
    drivetrain.move(0.0 + offset, 0.0 - offset);
  }

  @Override
  public void end(boolean interrupted) {
    drivetrain.move(0, 0);
  }

  @Override
  public boolean isFinished() {
    double averageEncoderPosition = (drivetrain.getLeftEncoderPosition() + drivetrain.getRightEncoderPosition()) / 2;
    return averageEncoderPosition > targetEncoderPosition;
  }
}
