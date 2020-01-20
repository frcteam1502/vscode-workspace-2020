/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Spinner extends SubsystemBase {

  CANSparkMax lift, spinner;
  DigitalInput liftLimit;
  ColorSensorV3 colorSensor;
  Color lastColor = Color.kBlue;
  int counter = 0;

  public Spinner(DigitalInput liftLimit, ColorSensorV3 colorSensor, CANSparkMax lift, CANSparkMax spinner) {
    this.lift = lift;
    this.spinner = spinner;
    this.liftLimit = liftLimit;
    this.colorSensor = colorSensor;
  }

  public void runSpinner() {
    while (!liftLimit.get()) lift.set(1);
    lift.set(0);
    while (counter < 6) {
      if (!lastColor.equals((Color) colorSensor.getColor())) counter++;
      spinner.set(1);
    }

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
