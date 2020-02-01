/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Lidar;

public class LidarDriveTrain extends SubsystemBase {
  /**
   * Creates a new LidarDriveTrain.
   */
  CANSparkMax DRIVE_FRONT_LEFT;
  CANSparkMax DRIVE_BACK_LEFT;
  CANSparkMax DRIVE_FRONT_RIGHT;
  CANSparkMax DRIVE_BACK_RIGHT;
  Lidar lidar;

  public LidarDriveTrain(CANSparkMax DRIVE_FRONT_LEFT, CANSparkMax DRIVE_BACK_LEFT, CANSparkMax DRIVE_FRONT_RIGHT,
      CANSparkMax DRIVE_BACK_RIGHT, Lidar lidar) {
    this.DRIVE_BACK_LEFT = DRIVE_BACK_LEFT;
    this.DRIVE_BACK_RIGHT = DRIVE_BACK_RIGHT;
    this.DRIVE_FRONT_LEFT = DRIVE_FRONT_LEFT;
    this.DRIVE_FRONT_RIGHT = DRIVE_FRONT_RIGHT;
    this.lidar = lidar;
  }

  public double getDistance() {
    return lidar.getDistance();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
