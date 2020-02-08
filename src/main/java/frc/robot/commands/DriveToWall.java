/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;

public class DriveToWall extends DriveStraight {

  static final double STOPPING_DISTANCE_CM = 20;

  public DriveToWall(Drivetrain drivetrain) {
    super(drivetrain);
  }

  @Override
  public void execute() {
    super.execute();
    SmartDashboard.putNumber("lidar distance", Constants.Sensors.LIDAR.getDistance());
  }

  @Override
  public boolean isFinished() {
    return Constants.Sensors.LIDAR.getDistance() <= STOPPING_DISTANCE_CM;
  }
}
