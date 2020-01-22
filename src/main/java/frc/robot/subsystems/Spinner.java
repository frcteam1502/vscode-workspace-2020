/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorSensorV3.RawColor;

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

  // red green blue yellow. Need to fix later
  final Color RED = new Color(.602539, .300049, .097412);
  final Color GREEN = new Color(.421875, .472656, .105469);
  final Color BLUE = new Color(.234863, .528564, .236572);
  final Color YELLOW = new Color(.184814, .422119, .393066);
  final Color[] colorMap = {RED, GREEN, BLUE, YELLOW};

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
  class Color {

    private double [] RGB = new double[3];

    public Color (RawColor color) {
      double mag = color.red + color.green + color.blue;
      double [] RGB = {color.red / mag, color.green / mag, color.blue / mag};
      this.RGB = RGB;
    }

    public Color (double... RGB) {
      if (RGB.length == 3) this.RGB = RGB;
      else throw new IllegalArgumentException();
    }

    public boolean compareTo(Color compare) {
      return this.RGB == compare.RGB;
    }

    public double getThreshHold(Color compare) {
      return Math.pow(this.RGB[0] - RGB[0], 2) + Math.pow(this.RGB[1] - RGB[1], 2) + Math.pow(this.RGB[2] - RGB[2], 2);
    }
  }

  public Color expectColor(Color last) {
    Color expected = null;
    for (int i = 0; i < colorMap.length; i++) {
      try {
        if (colorMap[i] == last) expected = colorMap[i + 1];
      }
      catch (ArrayIndexOutOfBoundsException e) {
        expected = colorMap[0];
      }
    }
    return expected;
  }

  /**
   * @return status of completion
   * Completed if the limit switch returns true.
   * If the limit switch returns true if the limit switch is not being pressed, this needs to be flip flopped.
   */
  public boolean moveLift() {
    boolean done = false;
    if (!liftLimit.get()) lift.set(1);
    else {
      done = true;
      lift.set(0);
    }
    return done;
  }

  public Color getColor() {
    return new Color(colorSensor.getRawColor());
  }

  /**
   * I... uh... im not too sure
   * All contingent on @see {@link #moveLift()}
   * Last color and Start color are initiated to the same color.
   * Start color is final, last color changes if the current color is valid, different than the last color, and is expected<br></br>
   * Valid color is considered any color with a threshhold of .1 or less.
   * Current color, for all intents and purposes, is switched to the color it is closest to.
   * If that color and the last color are exactly the same (remeber, youre no longer comparing a random rgb, but a predefined one).
   * then nothing happens.
   * If they arent the same, it checks the color is is against the predefined order of colors.
   * If it is the expected color, the color sensor is considered to have completed a color change.
   * If the current color is different than the last color, and the same as the first color, than one is added to the counter.
   */
  public void runSpinner() {
    if (moveLift()) {
      Color lastColor = getColor();
      final Color startColor = getColor();
      if (counter < 8) {
        Color currentColor = getColor();
        Double threshHold = null;
        Color tCurrentColor= null;
        for (Color color : colorMap) {
          double tThreshHold = color.getThreshHold(currentColor);
          if (threshHold == null || tCurrentColor == null || threshHold < tThreshHold) {
            threshHold = tThreshHold;
            tCurrentColor = color;
          }
        }
        currentColor = tCurrentColor;
        if (threshHold < .1 && !currentColor.compareTo(lastColor)) {
          if (expectColor(lastColor) == currentColor) {
            lastColor = currentColor;
            if (currentColor == startColor) counter++;
          }
          spinner.set(1);
        }
      }
      spinner.set(0);
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
