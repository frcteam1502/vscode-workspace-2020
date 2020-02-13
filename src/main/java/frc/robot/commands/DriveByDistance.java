/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.Drivetrain;

public class DriveByDistance extends DriveStraight {

  static final double INCHES_PER_ENCODER_TICK = 89.0 / 50.0;
  double targetEncoderPosition;

  public DriveByDistance(Drivetrain drivetrain, double inchesToGo) {
    super(drivetrain);
    targetEncoderPosition = inchesToGo / INCHES_PER_ENCODER_TICK;
  }

  @Override
  public boolean isFinished() {
    return drivetrain.getAverageEncoderPosition() > targetEncoderPosition;
  }
}
