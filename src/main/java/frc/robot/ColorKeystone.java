package frc.robot;

import edu.wpi.first.wpilibj.util.Color;

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
      if (this.getDifference(constant) < .3 && this.getDifference(constant) < this.getDifference(min))
        min = constant;
    }
    return min;
  }

  private double getDifference(ColorKeystone compare) {
    try {
      double differenceRed = this.rgb[0] - compare.rgb[0];
      double differenceGreen = this.rgb[1] - compare.rgb[1];
      double differenceBlue = this.rgb[2] - compare.rgb[2];
      return Math.abs(differenceRed) + Math.abs(differenceGreen) + Math.abs(differenceBlue);
    } catch (NullPointerException e) {
      return 3;
    }
  }
}