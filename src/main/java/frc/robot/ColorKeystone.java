package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;

public class ColorKeystone {

  final double[] rgb;
  public final String name;
  final static ColorKeystone RED = new ColorKeystone("red", .5, .35, .14);
  final static ColorKeystone YELLOW = new ColorKeystone("yellow", .32, .55, .13);
  final static ColorKeystone GREEN = new ColorKeystone("green", .173, .563, .264);
  final static ColorKeystone BLUE = new ColorKeystone("blue", .128, .41, .46);
  final static ColorKeystone[] COLOR_MAP = { YELLOW, RED, GREEN, BLUE };

  private ColorKeystone(String name, double... rgb) {
    this.name = name;
    this.rgb = rgb;
  }

  public static ColorKeystone getInstance(Color rawColor) {
    return new ColorKeystone("current", rawColor.red, rawColor.green, rawColor.blue);
  }

  public double[] getRGB() {
    return rgb;
  }

  public ColorKeystone getClosestColor() {
    ColorKeystone min = null;
    for (ColorKeystone constant : COLOR_MAP) {
      double constantDiff = this.getDifference(constant);
      SmartDashboard.putNumber("Constant difference", constantDiff);
      double minDiff = this.getDifference(min);
      SmartDashboard.putNumber("Mininum difference", constantDiff);
      if (constantDiff < .1 && constantDiff < minDiff)
        min = constant;
    }
    return min;
  }

  private double getDifference(ColorKeystone compare) {
    try {
      double differenceRed = this.rgb[0] - compare.rgb[0];
      double differenceGreen = this.rgb[1] - compare.rgb[1];
      double differenceBlue = this.rgb[2] - compare.rgb[2];
      return Math.pow(differenceRed, 2) + Math.pow(differenceGreen, 2) + Math.pow(differenceBlue, 2);
    } catch (NullPointerException e) {
      return 3;
    }
  }
}