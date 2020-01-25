/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class NavX extends SubsystemBase {
  /**
   * Creates a new NavX.
   * 
   */
  AHRS gyro;

  public NavX(AHRS gyro) {
    this.gyro = gyro;
  }

  public double getAngle() {
    return gyro.getAngle();
  }

  public double getRoll() {
    return gyro.getRoll();
  }

  public void outputDataToSmartDashboard() {
    SmartDashboard.putNumber("Pitch: ", gyro.getPitch());
    SmartDashboard.putNumber("Roll; ", gyro.getRoll());
    SmartDashboard.putNumber("Yaw; ", gyro.getYaw());
    SmartDashboard.putNumber("CompassHeading; ", gyro.getCompassHeading());
    SmartDashboard.putBoolean("isCalibrating; ", gyro.isCalibrating());
    SmartDashboard.putBoolean("isConnected; ", gyro.isConnected());
    SmartDashboard.putNumber("WorldLinearAccelX; ", gyro.getWorldLinearAccelX());
    SmartDashboard.putNumber("WorldLinearAccelY; ", gyro.getWorldLinearAccelY());
    SmartDashboard.putNumber("WorldLinearAccelZ; ", gyro.getWorldLinearAccelZ());
    SmartDashboard.putBoolean("isMoving; ", gyro.isMoving());
    SmartDashboard.putBoolean("isRotating; ", gyro.isRotating());
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
