/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LiftAdjust extends SubsystemBase {
  private final CANSparkMax adjuster;
  private final ADXRS450_Gyro gyro;

  public LiftAdjust(CANSparkMax adjuster, ADXRS450_Gyro gyro) {
    this.adjuster = adjuster;
    this.gyro = gyro;
  }

  public void adjust(double correction) {
    adjuster.set(correction);
  }

  public double getAngle() {
    return gyro.getAngle();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
