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
  private final ColorHelper RED = new ColorHelper("red", .602539, .300049, .097412);
  private final ColorHelper GREEN = new ColorHelper("green", .421875, .472656, .105469);
  private final ColorHelper BLUE = new ColorHelper("blue", .234863, .528564, .236572);
  private final ColorHelper YELLOW = new ColorHelper("yellow", .184814, .422119, .393066);
  private final ColorHelper[] COLOR_MAP = { YELLOW, RED, GREEN, BLUE };

  /**
   * Creates a new ColorTest.
   */
  public ColorTest(ColorSensorV3 sensor) {
    this.sensor = sensor;
  }

  private class ColorHelper extends Color {

    private double[] RGB = new double[3];
    private String name;

    public ColorHelper(Color color) {
      super(color.red, color.green, color.blue);
      double mag = color.red + color.green + color.blue;
      double[] RGB = { color.red / mag, color.green / mag, color.blue / mag };
      this.RGB = RGB;
    }

    /**
     * @param RGB must have length of 3
     */
    public ColorHelper(String name, double... RGB) {
      super(RGB[0], RGB[1], RGB[2]);
      this.RGB = RGB;
      this.name = name;
    }

    /**
     * @param compare compared either directly based on their respected RGB values,
     *                or their proximity to each other via
     *                {@link #getDifference(ColorHelper)}
     * @return whether or not the two colors are equal
     */
    public boolean compareTo(ColorHelper compare) {
      return this.RGB == compare.RGB || getDifference(compare) < .1;
    }

    /**
     * @return Returns the closest color from COLOR_MAP
     */
    public ColorHelper getClosestColor() {
      ColorHelper closestColor = null;
      for (ColorHelper wheelColor : COLOR_MAP) {
        if (closestColor == null || wheelColor.getDifference(this) < closestColor.getDifference(this)) {
          closestColor = wheelColor;
        }
      }
      return closestColor;
    }

    private double getDifference(ColorHelper compare) {
      return Math.pow(this.RGB[0] - compare.RGB[0], 2) + Math.pow(this.RGB[1] - compare.RGB[1], 2)
          + Math.pow(this.RGB[2] - compare.RGB[2], 2);
    }

    /**
     * @return expected next color on the wheel (clockwise) based on the index of
     *         {@link #colorMap}
     */
    public ColorHelper nextColor() {
      ColorHelper expected = null;
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

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
