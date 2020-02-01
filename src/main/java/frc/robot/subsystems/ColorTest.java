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
import frc.robot.commands.ColorTestCommand;

public class ColorTest extends SubsystemBase {

  private ColorSensorV3 colorSensor;

  public ColorTest(ColorSensorV3 colorSensor) {
    this.colorSensor = colorSensor;
    setDefaultCommand(new ColorTestCommand(this));
  }

  public void run() {
    ColorKeystone current = ColorKeystone.getInstance(colorSensor.getColor());
    ColorKeystone constant = current.getClosestColor();
    SmartDashboard.putNumber("Current red", current.getRGB()[0]);
    SmartDashboard.putNumber("Current green", current.getRGB()[1]);
    SmartDashboard.putNumber("Current blue", current.getRGB()[2]);
    SmartDashboard.putNumber("constant red", constant.getRGB()[0]);
    SmartDashboard.putNumber("constant green", constant.getRGB()[1]);
    SmartDashboard.putNumber("constant blue", constant.getRGB()[2]);
    SmartDashboard.putString("Name", constant.name);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
