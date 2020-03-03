/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Lidar;
import frc.robot.subsystems.Drivetrain;

public class DriveToWall extends DriveStraight {

  static final double STOPPING_DISTANCE_CM = 30;
  Lidar lidar;

  public DriveToWall(Drivetrain drivetrain, Lidar lidar) {
    super(drivetrain);
    this.lidar = lidar;
  }

  @Override
  public void execute() {
    super.execute();
    SmartDashboard.putNumber("lidar distance", lidar.getDistance());
  }

  @Override
  public void end(boolean isFinished) {
    drivetrain.move(0, 0);
  }

  @Override
  public boolean isFinished() {
    return lidar.getDistance() <= STOPPING_DISTANCE_CM;
  }
}
