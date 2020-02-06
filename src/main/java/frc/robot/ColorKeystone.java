package frc.robot;

import edu.wpi.first.wpilibj.util.Color;

public class ColorKeystone extends Color{

  private final double[] rgb;
  private final static ColorKeystone RED = new ColorKeystone(.5, .35, .14);
  private final static ColorKeystone YELLOW = new ColorKeystone(.32, .55, .13);
  private final static ColorKeystone GREEN = new ColorKeystone(.173, .563, .264);
  private final static ColorKeystone BLUE = new ColorKeystone(.128, .41, .46);
  private final static ColorKeystone[] COLOR_MAP = { YELLOW, RED, GREEN, BLUE };

  private ColorKeystone(double... rgb) {
    super(rgb[0], rgb[1], rgb[2]);
    this.rgb = rgb;
  }

  public static ColorKeystone getClosestColor(Color currentColor) {
    ColorKeystone current = new ColorKeystone(currentColor.red, currentColor.green, currentColor.blue);
    ColorKeystone min = null;
    for (ColorKeystone constant : COLOR_MAP) {
      if (current.getDifference(constant) < .1 && current.getDifference(constant) < current.getDifference(min))
        min = constant;
    }
    return min;
  }

  private double getDifference(ColorKeystone compare) {
    return compare == null ? 3 : Math.pow(rgb[0] - compare.rgb[0], 2) + Math.pow(rgb[1] - compare.rgb[1], 2) + Math.pow(rgb[2] - compare.rgb[2], 2);
  }
}