/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.ADXL345_I2C;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Joysticks;

public class SkidControl extends SubsystemBase {
  /**
   * Creates a new SkidControl.
   */

  ADXL345_I2C accelerometer;

  private final double minMovement = 0.01;
  CANSparkMax motor;

  public SkidControl(final ADXL345_I2C accelerometer) {
    this.accelerometer = accelerometer;
  }

  public boolean isSkidding() {
    return accelerometer.getZ() < motor.getEncoder().getVelocity() - .05
        || accelerometer.getZ() > motor.getEncoder().getVelocity() + .05;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
