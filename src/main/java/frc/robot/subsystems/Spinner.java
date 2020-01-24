/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.ArrayList;
import java.util.Collection;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Spinner extends SubsystemBase {

  CANSparkMax spinWheel;
  CANSparkMax spinLift;
  DigitalInput liftLimit;
  double wheelSpeed = 1;
  ColorSensorV3 wheelColor;
  int colorCount = 0;
  int rotationCount = 0;
  Color lastColor = null;
  final int colorsPerRotation = 8;
  final int totalOfRotations = 3;
  final double diffrenceSumThreshhold = 0.15;

  public Spinner(CANSparkMax spinLift, CANSparkMax spinWheel, DigitalInput liftLimit, ColorSensorV3 wheelColor) {
    this.spinLift = spinLift;
    this.spinWheel = spinWheel;
    this.liftLimit = liftLimit;
    this.wheelColor = wheelColor;
  }
  /*
   * public void SpinLift(double placeholder1) { spinLift.set(placeholder1); }
   * 
   * public void SpinWheel(double placeholder2) { spinWheel.set(placeholder2); }
   */

  public void run() {

    if (!liftLimit.get())
      spinLift.set(1);
    else if (rotationCount < totalOfRotations) {
      spinLift.set(0);
      if (lastColor == null) {
        lastColor = wheelColor.getColor();
        spinWheel.set(wheelSpeed);
      } else {
        Color currentColor = wheelColor.getColor();
        Color difColor = getColorDiffernce(lastColor, currentColor);
        double dif = difColor.red + difColor.blue + difColor.green;
        if (dif >= diffrenceSumThreshhold) {
          colorCount++;
          if (colorCount % colorsPerRotation == 0) {
            wheelSpeed = wheelSpeed - .15;
            spinWheel.set(wheelSpeed);
            rotationCount++;
          }
        }
        lastColor = currentColor;
      }
    }
    /*
     * else if current color is not desired { // TODO add go to color
     * 
     * }
     */
    else {
      spinWheel.set(0);
    }
    /*
     * CANEncoder enc = spinWheel.getEncoder(); if (enc.getPosition() < 10000)
     * spinWheel.set(1); else spinWheel.set(0);
     */
  }

  public Color getColorDiffernce(Color color1, Color color2) {
    double color1Red = color1.red;
    double color1Green = color1.green;
    double color1Blue = color1.blue;
    double color2Red = color2.red;
    double color2Green = color2.green;
    double color2Blue = color2.blue;

    double redDifference = color1Red - color2Red;
    double greenDiffernce = color1Green - color2Green;
    double blueDiffernce = color1Blue - color2Blue;

    Color colorDiffernce = new Color(redDifference, greenDiffernce, blueDiffernce);
    return colorDiffernce;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
