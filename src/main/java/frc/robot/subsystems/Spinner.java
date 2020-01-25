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

import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Spinner implimented based on amount of times a single color goes past
 */
public class Spinner extends SubsystemBase {

  private final CANSparkMax spinner;
  private final ColorSensorV3 colorSensor;
  private int counter = 0;

  private Color startColor;
  private Color lastColor;
  // red green blue yellow. Need to fix later
  private final Color RED = new Color(.602539, .300049, .097412);
  private final Color GREEN = new Color(.421875, .472656, .105469);
  private final Color BLUE = new Color(.234863, .528564, .236572);
  private final Color YELLOW = new Color(.184814, .422119, .393066);
  private final Color[] COLOR_MAP = { YELLOW, RED, GREEN, BLUE };

  public Spinner(ColorSensorV3 colorSensor, CANSparkMax spinner) {
    this.spinner = spinner;
    this.colorSensor = colorSensor;
  }

  /**
   * Previous color class didnt allow me to easily make new Colors, so i ditched
   * it
   */
  private class Color {

    private double[] RGB = new double[3];

    public Color(RawColor color) {
      double mag = color.red + color.green + color.blue;
      double[] RGB = { color.red / mag, color.green / mag, color.blue / mag };
      this.RGB = RGB;
    }

    /**
     * @param RGB must have length of 3
     */
    public Color(double... RGB) {
      if (RGB.length == 3)
        this.RGB = RGB;
      else
        throw new IllegalArgumentException();
    }

    /**
     * @param compare compared either directly based on their respected RGB values,
     *                or their proximity to each other via
     *                {@link #getDifference(Color)}
     * @return whether or not the two colors are equal
     */
    public boolean compareTo(Color compare) {
      return this.RGB == compare.RGB || getDifference(compare) < .1;
    }

    /**
     * Returns the closest color from COLOR_MAP
     * 
     * @param color
     * @return
     */
    public Color getClosestColor() {
      Color closestColor = null;
      for (Color wheelColor : COLOR_MAP) {
        if (closestColor == null || wheelColor.getDifference(this) < closestColor.getDifference(this)) {
          closestColor = wheelColor;
        }
      }
      return closestColor;
    }

    private double getDifference(Color compare) {
      return Math.pow(this.RGB[0] - compare.RGB[0], 2) + Math.pow(this.RGB[1] - compare.RGB[1], 2)
          + Math.pow(this.RGB[2] - compare.RGB[2], 2);
    }

    /**
     * @return expected next color on the wheel (clockwise) based on the index of
     *         {@link #colorMap}
     */
    public Color nextColor() {
      Color expected = null;
      for (int i = 0; i < COLOR_MAP.length; i++) {
        if (this.compareTo(COLOR_MAP[i])) {
          try {
            expected = COLOR_MAP[i + 1];
          } catch (ArrayIndexOutOfBoundsException e) {
            expected = COLOR_MAP[0];
          }
        }
      }
      return expected;
    }
  }

  /**
   * Sets inital values of startColor and lastColor. startColor should be
   * effectively final lastColor is changed in @see {@link #runSpinner()}
   */
  public void setColor() {
    startColor = getColor().getClosestColor();
    lastColor = getColor().getClosestColor();
  }

  /**
   * Referring to the motorController
   * 
   * @param speed
   */
  public void setSpinner(double speed) {
    spinner.set(speed);
  }

  /**
   * @return Color the sensor is looking at in Color format
   */
  public Color getColor() {
    return new Color(colorSensor.getRawColor());
  }

  /**
   * @return whether the spinner has met its end condition
   */
  public boolean inPlace() {
    return counter == 8;
  }

  /**
   * Does fact checking, should make it more rigorous.
   */
  public void runSpinner() {
    Color currentColor = getColor();
    if (!currentColor.compareTo(lastColor) && currentColor.compareTo(lastColor.nextColor())) {
      lastColor = lastColor.nextColor();
      if (currentColor.compareTo(startColor))
        counter++;
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
