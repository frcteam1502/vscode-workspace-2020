package frc.robot;

import edu.wpi.first.wpilibj.util.Color;

public class ColorKeystone extends Color {

  private final double[] rgb;
  private final static ColorKeystone RED = new ColorKeystone("R", .4, .4, .2);
  private final static ColorKeystone YELLOW = new ColorKeystone("Y", .31, .51, .16);
  private final static ColorKeystone GREEN = new ColorKeystone("G", .22, .51, .26);
  private final static ColorKeystone BLUE = new ColorKeystone("B", .18, .43, .38);
  // private final static ColorKeystone[] COLOR_MAP = { YELLOW, RED, GREEN, BLUE
  // };
  // private final static ColorKeystone[] SENSOR_COLOR_MAP = { GREEN, BLUE,
  // YELLOW, RED };

  private final static ColorEntry[] COLOR_MAP = { new ColorEntry(YELLOW, GREEN), new ColorEntry(RED, BLUE),
      new ColorEntry(GREEN, YELLOW), new ColorEntry(BLUE, RED) };

  private final String identifier;

  private ColorKeystone(String identifier, double... rgb) {
    super(rgb[0], rgb[1], rgb[2]);
    this.rgb = rgb;
    this.identifier = identifier;
  }

  public static class ColorEntry {

    private final ColorKeystone robot, actual;

    public ColorEntry(ColorKeystone robot, ColorKeystone actual) {
      this.robot = robot;
      this.actual = actual;
    }

    public ColorKeystone getRobotColor() {
      return robot;
    }

    public ColorKeystone getActualColor() {
      return actual;
    }
  }

  public static ColorEntry getClosestColor(Color currentColor) {
    ColorKeystone current = new ColorKeystone("n", currentColor.red, currentColor.green, currentColor.blue);
    ColorEntry min = null;
    for (ColorEntry entry : COLOR_MAP) {
      if (current.getDifference(entry.getRobotColor()) < .1
          && current.getDifference(entry.getRobotColor()) < current.getDifference(min.getRobotColor()))
        min = entry;
    }
    return min;
  }

  private double getDifference(ColorKeystone compare) {
    return compare == null ? 3
        : Math.pow(rgb[0] - compare.rgb[0], 2) + Math.pow(rgb[1] - compare.rgb[1], 2)
            + Math.pow(rgb[2] - compare.rgb[2], 2);
  }

  public String getIdentifier() {
    return identifier;
  }
}