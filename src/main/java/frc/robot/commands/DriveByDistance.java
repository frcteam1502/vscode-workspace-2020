/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.ConversionFactors;
import frc.robot.subsystems.Drivetrain;

public class DriveByDistance extends DriveStraight {

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
  public boolean isFinished() {
    return drivetrain.getAverageEncoderPosition() > targetEncoderPosition;
  }
}
