/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Lidar;

public class LidarSubsystem extends SubsystemBase {
  Lidar lidar;
  double disCM;
  double disIN;
  double disM;

  /**
   * Creates a new Lidar.
   */
  public LidarSubsystem(Lidar lidar) {
    this.lidar = lidar;
  }

  public void getDistance() {
    disCM = lidar.getDistance();
    disIN = disCM / 2.54;
    disM = disCM / 100;
    SmartDashboard.putNumber("Distance (CM): ", disCM);
    SmartDashboard.putNumber("Distance (IN): ", disIN);
    SmartDashboard.putNumber("Distance (M): ", disM);
  }

  public double getCM() {
    return disCM;
  }

  public double getIN() {
    return disIN;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    getDistance();
  }
}
