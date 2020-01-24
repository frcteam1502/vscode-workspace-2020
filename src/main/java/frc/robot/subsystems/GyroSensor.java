/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.ADXL345_I2C;
import edu.wpi.first.wpilibj.ADXL345_SPI;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.ADXL345_I2C.Axes;
import edu.wpi.first.wpilibj.interfaces.Accelerometer.Range;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.DisplayGyroPosition;

public class GyroSensor extends SubsystemBase {
  /**
   * Creates a new GyroSensor.
   */
  ADXL345_I2C gyro = new ADXL345_I2C(I2C.Port.kOnboard, Range.k4G);

  public GyroSensor(DisplayGyroPosition m_GyroPostionGetting) {

  }

  public void putToSmartDashboard() {
    SmartDashboard.putNumber("Gyro X", gyro.getX());
    SmartDashboard.putNumber("Gyro Y", gyro.getY());
    SmartDashboard.putNumber("Gyro Z", gyro.getZ());
    SmartDashboard.putNumber("X Axis Acceleration", gyro.getAcceleration(Axes.kX));
    SmartDashboard.putNumber("Y Axis Acceleration", gyro.getAcceleration(Axes.kY));
    SmartDashboard.putNumber("Z Axis Acceleration", gyro.getAcceleration(Axes.kZ));
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
