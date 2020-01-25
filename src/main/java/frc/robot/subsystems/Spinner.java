/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Map;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
/**
 * Spinner implimented based on amount of times a single color goes past
 */
public class Spinner extends SubsystemBase {

  CANSparkMax lift, spinner;
  DigitalInput liftLimit;
  ColorSensorV3 colorSensor;
  int counter = 0;

  public Spinner(DigitalInput liftLimit, ColorSensorV3 colorSensor, CANSparkMax lift, CANSparkMax spinner) {
    this.lift = lift;
    this.spinner = spinner;
    this.liftLimit = liftLimit;
    this.colorSensor = colorSensor;
  }

  /**
   * Decided to ditch the old implimentation of Color
   * useing exlucisvely the RGB values the thing spits out
   * @see Color.class
   */
  enum Colors {
    // Edit these colors. No matter what, this needs to be changed
    Blue(.234863, .528564, .236572),
    Red(.602539, .300049, .097412),
    Green(.421875, .472656, .105469),
    Yellow(.184814, .422119, .393066);

    public double [] RGB;

    Colors (double... RGB) {
      this.RGB = RGB;
    }

    /**
     * very very difficult to make, but i stand by this.
     * @param RGB target RGB value that is to be compared
     * @return a Map.Entry containing the closest possible Color and the threshHoldValue implimented as per Robbies orders
     */
    public static Map.Entry<Colors, Double> threshHoldFind(double [] RGB) {
      Map.Entry<Colors, Double> threshHoldEntry = new AbstractMap.SimpleEntry<Colors, Double>(null, null);
      for (Colors color : Colors.values()) {
        double threshHoldValue = Math.pow(color.RGB[0] - RGB[0], 2) + Math.pow(color.RGB[1] - RGB[1], 2) + Math.pow(color.RGB[2] - RGB[2], 2);
        if ((threshHoldEntry.getValue() == null || threshHoldEntry.getKey() == null) || threshHoldEntry.getValue() < threshHoldValue)
          threshHoldEntry = new AbstractMap.SimpleEntry<Colors, Double>(color, threshHoldValue);
      }
      return threshHoldEntry;
    }
  }

  /**
   * runSpinner was designed to be completely automatic <br>
   * </br><b>Points of contention</b>
   * <ul>
   * <li>Wish targetRGB could be obtained easier
   * <li>not to sure on the output of compareColors being > 0
   * </ul>
   */
  public void runSpinner() {
    while (!liftLimit.get()) lift.set(1);
    lift.set(0);
    double [] lastColor = {colorSensor.getColor().red, colorSensor.getColor().green, colorSensor.getColor().blue};
    while (counter < 8) {
      double[] targetRGB = {colorSensor.getColor().red, colorSensor.getColor().green, colorSensor.getColor().blue};
      if (Colors.threshHoldFind(targetRGB).getValue() < .1 && compareColors(Colors.threshHoldFind(lastColor).getKey().RGB, Colors.threshHoldFind(targetRGB).getKey().RGB) > 0) {
        counter++;
        lastColor = Colors.threshHoldFind(targetRGB).getKey().RGB;
      }
      spinner.set(1);
    }
    spinner.set(0);
  }
  

  private double compareColors(double[] color1, double[] color2) {
    if ((color1.length == 0 || color2.length == 0) || color1.length != color2.length) throw new NullPointerException();
    double [] differenceInRgb = {Math.abs(color1[0] - color2[0]), Math.abs(color1[1] - color2[1]), Math.abs(color1[2] - color2[2])};
    return Arrays.stream(differenceInRgb).average().orElse(Double.NaN);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
