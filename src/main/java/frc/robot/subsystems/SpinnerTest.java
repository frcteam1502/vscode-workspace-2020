/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.ColorKeystone;
import frc.robot.commands.ColorTest;

public class SpinnerTest extends SubsystemBase {

  ColorSensorV3 sensor;

  public SpinnerTest(ColorSensorV3 sensor) {
    setDefaultCommand(new ColorTest(this));
    this.sensor = sensor;
  }

  public void run() {
    double[] rgb = ColorKeystone.getClosestColor(sensor.getColor()).getRGB();

    SmartDashboard.putNumber("Red", rgb[0]);
    SmartDashboard.putNumber("Green", rgb[1]);
    SmartDashboard.putNumber("Blue", rgb[2]);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
