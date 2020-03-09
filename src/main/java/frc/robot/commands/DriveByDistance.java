/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.Constants.ConversionFactors;
import frc.robot.subsystems.Drivetrain;

public class DriveByDistance extends DriveStraight {

  static final double MIN_SPEED = 0.2;
  static final double MAX_SPEED = 0.7;
  static final double POWER_PER_METER_ACCELERATION = 0.4;
  double targetEncoderPosition;

  public DriveByDistance(Drivetrain drivetrain, double inchesToGo) {
    super(drivetrain);
    targetEncoderPosition = inchesToGo / ConversionFactors.INCHES_PER_ENCODER_VALUE;

  }

  public void execute() {
    super.execute();
    SmartDashboard.putNumber("Encoder position", drivetrain.getAverageEncoderPosition());
    SmartDashboard.putNumber("Encoder target", targetEncoderPosition);
  }

  @Override
  protected double getVelocity() {
    double position = drivetrain.getAverageEncoderPosition();
    double distanceFromStart = Math.abs(position);
    double distanceFromEnd = Math.abs(targetEncoderPosition - position);
    double minDistanceInMeters = Math.min(distanceFromStart, distanceFromEnd)
        * Constants.ConversionFactors.CENTIMETERS_PER_ENCODER_VALUE * 100;
    return -0.2;
    // return -Math.min(MIN_SPEED + minDistanceInMeters *
    // POWER_PER_METER_ACCELERATION, MAX_SPEED);
  }

  @Override
  public boolean isFinished() {
    return Math.abs(drivetrain.getAverageEncoderPosition()) > Math.abs(targetEncoderPosition);
  }
}
