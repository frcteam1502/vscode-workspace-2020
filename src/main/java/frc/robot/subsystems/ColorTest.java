/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ColorTest extends SubsystemBase {
  private ColorSensorV3 sensor;

  /**
   * Creates a new ColorTest.
   */
  public ColorTest(ColorSensorV3 sensor) {
    this.sensor = sensor;
  }

  public boolean isRed() {
    Color color = Color.kFirstRed;
    return color.equals(sensor.getColor());
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
