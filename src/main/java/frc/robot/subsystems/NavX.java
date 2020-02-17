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
  AHRS NAVX;

  public NavX(AHRS NAVX) {
    this.NAVX = NAVX;
  }

  public double getAngle() {
    return NAVX.getAngle();
  }

  public double getRoll() {
    return NAVX.getRoll();
  }

  public void outputDataToSmartDashboard() {
    SmartDashboard.putNumber("Pitch: ", NAVX.getPitch());
    SmartDashboard.putNumber("Roll; ", NAVX.getRoll());
    SmartDashboard.putNumber("Yaw; ", NAVX.getYaw());
    SmartDashboard.putNumber("CompassHeading; ", NAVX.getCompassHeading());
    SmartDashboard.putBoolean("isCalibrating; ", NAVX.isCalibrating());
    SmartDashboard.putBoolean("isConnected; ", NAVX.isConnected());
    SmartDashboard.putNumber("WorldLinearAccelX; ", NAVX.getWorldLinearAccelX());
    SmartDashboard.putNumber("WorldLinearAccelY; ", NAVX.getWorldLinearAccelY());
    SmartDashboard.putNumber("WorldLinearAccelZ; ", NAVX.getWorldLinearAccelZ());
    SmartDashboard.putBoolean("isMoving; ", NAVX.isMoving());
    SmartDashboard.putBoolean("isRotating; ", NAVX.isRotating());
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
