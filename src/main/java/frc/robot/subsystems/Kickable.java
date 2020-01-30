/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Kickable extends SubsystemBase {
  /**
   * Creates a new Kickable.
   */

  AHRS gyro;

  float initial = gyro.getYaw();

  public Kickable() {

  }

  public void fixRotation() {
    if (gyro.getYaw() > initial) {
      // Drivetrain.rightPwr = PIDController.getError();
    } else if (gyro.getYaw() < initial) {

    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
