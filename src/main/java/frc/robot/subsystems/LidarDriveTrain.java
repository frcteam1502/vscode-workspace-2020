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
import frc.robot.PIDController;

public class LidarDriveTrain extends SubsystemBase {
  /**
   * Creates a new LidarDriveTrain.
   */
  CANSparkMax DRIVE_FRONT_LEFT;
  CANSparkMax DRIVE_BACK_LEFT;
  CANSparkMax DRIVE_FRONT_RIGHT;
  CANSparkMax DRIVE_BACK_RIGHT;
  Lidar lidar;
  public static final double circumference = 6 * Math.PI * 2.54;
  public static final double unitConvertion = 6 * Math.PI / 10.9 * 2.54;
  public static final double time = 0.5;
  PIDController pid = new PIDController(0, 0, 0);

  public LidarDriveTrain(CANSparkMax DRIVE_FRONT_LEFT, CANSparkMax DRIVE_BACK_LEFT, CANSparkMax DRIVE_FRONT_RIGHT,
      CANSparkMax DRIVE_BACK_RIGHT, Lidar lidar, PIDController pid) {
    this.DRIVE_BACK_LEFT = DRIVE_BACK_LEFT;
    this.DRIVE_BACK_RIGHT = DRIVE_BACK_RIGHT;
    this.DRIVE_FRONT_LEFT = DRIVE_FRONT_LEFT;
    this.DRIVE_FRONT_RIGHT = DRIVE_FRONT_RIGHT;
    this.lidar = lidar;
    this.pid = pid;
  }

  public double getDistance() {
    return lidar.getDistance();
  }

  public void run() {
    double driveFrontLeftVelocity = DRIVE_FRONT_LEFT.getEncoder().getVelocity() * unitConvertion;
    double driveFrontRightVelocity = DRIVE_FRONT_RIGHT.getEncoder().getVelocity() * unitConvertion;
    double distanceLeft = driveFrontLeftVelocity * time;
    double distanceRight = driveFrontRightVelocity * time;
    double lidarDistance = getDistance();
    if (distanceLeft >= lidarDistance || distanceRight >= lidarDistance) {
      pid.D = lidarDistance;

    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
