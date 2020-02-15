/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.PIDController;
import frc.robot.subsystems.Drivetrain;

public class LidarStop extends CommandBase {

  static final double TARGET_DISTANCE = 20; // cm
  private static final double STOPPING_TIME = 0.5;
  Drivetrain drivetrain;
  private PIDController lidarStopController = new PIDController(2e-8, 0, 0);
  boolean hasReachedStoppingDistance;
  Supplier<Boolean> shouldFinish;

  public LidarStop(Drivetrain drivetrain, Supplier<Boolean> shouldFinish) {
    addRequirements(drivetrain);
    this.shouldFinish = shouldFinish;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    hasReachedStoppingDistance = false;
    lidarStopController.reset();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (!hasReachedStoppingDistance && isBeyondStoppingDistance()) {
      hasReachedStoppingDistance = true;
    }
    if (hasReachedStoppingDistance) {
      double error = Constants.Sensors.LIDAR.getDistance() - TARGET_DISTANCE;
      double correction = lidarStopController.getCorrection(error);
      drivetrain.move(correction, correction);
    } else
      drivetrain.move(0.5, 0.5);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drivetrain.move(0, 0);
  }

  private boolean isBeyondStoppingDistance() {
    double averageVel = (drivetrain.getLeftEncoderVelocity() + drivetrain.getRightEncoderVelocity()) / 2;
    double speedInCmPerSecond = averageVel * Constants.ConversionFactors.CENTIMETERS_PER_SECOND_PER_ENCODER_RPM;
    double distanceFromTarget = Constants.Sensors.LIDAR.getDistance() - TARGET_DISTANCE;
    double timeToReachDestination = distanceFromTarget / speedInCmPerSecond;
    SmartDashboard.putNumber("Time to reach destination", timeToReachDestination);
    return timeToReachDestination < STOPPING_TIME;
  }

  @Override
  public boolean isFinished() {
    return lidarStopController.isStable(2, 2000) || shouldFinish.get();
  }
}
