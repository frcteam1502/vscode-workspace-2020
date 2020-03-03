/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.PIDController;
import frc.robot.subsystems.Drivetrain;

public abstract class DriveStraight extends CommandBase {
  static final double SPEED = 0.2;
  double initialHeading;
  Drivetrain drivetrain;
  PIDController rotationController = new PIDController(5e-3, 0, 0);

  public DriveStraight(Drivetrain drivetrain) {
    this.drivetrain = drivetrain;
    addRequirements(drivetrain);
  }

  @Override
  public void initialize() {
    initialHeading = Constants.Sensors.GYRO.getAngle();
    rotationController.reset();
    drivetrain.resetEncoders();
  }

  @Override
  public void execute() {
    double error = Constants.Sensors.GYRO.getAngle() - initialHeading;
    double offset = rotationController.getCorrection(error);
    drivetrain.move(getVelocity() - offset, getVelocity() + offset);
  }

  @Override
  public void end(boolean interrupted) {
    drivetrain.move(0, 0);
  }

  protected double getVelocity() {
    return -SPEED;
  }

  public abstract boolean isFinished();
}
