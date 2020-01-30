/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.util.Color;

/**
 * Add your docs here.
 */
public class ColorKeystone {

  final double[] rgb;
  final static ColorKeystone RED = new ColorKeystone(.602539, .300049, .097412);
  final static ColorKeystone GREEN = new ColorKeystone(.421875, .472656, .105469);
  final static ColorKeystone BLUE = new ColorKeystone(.234863, .528564, .236572);
  final static ColorKeystone YELLOW = new ColorKeystone(.184814, .422119, .393066);
  final static ColorKeystone[] COLOR_MAP = { YELLOW, RED, GREEN, BLUE };

  private ColorKeystone(double... rgb) {
    this.rgb = rgb;
  }

  public static ColorKeystone getInstance(Color rawColor) {
    return new ColorKeystone(rawColor.red, rawColor.green, rawColor.blue);
  }

  public double[] getRGB() {
    return rgb;
  }

  public ColorKeystone getClosestColor() {
    ColorKeystone min = null;
    for (ColorKeystone constant : COLOR_MAP) {
      if (this.getDifference(constant) < .1 && this.getDifference(constant) < this.getDifference(min))
        min = constant;
    }
    return min;
  }

  private double getDifference(ColorKeystone compare) {
    try {
      return Math.pow(this.rgb[0] - compare.rgb[0], 2) + Math.pow(this.rgb[1] - compare.rgb[1], 2)
          + Math.pow(this.rgb[2] - compare.rgb[2], 2);
    } catch (NullPointerException e) {
      return 1;
    }
  }
}