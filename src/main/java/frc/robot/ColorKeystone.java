package frc.robot;

public class ColorKeystone {
  private final String identifier;
  private final double[] rgb;

  private final static ColorKeystone RED = new ColorKeystone("R", .4, .4, .2);
  private final static ColorKeystone YELLOW = new ColorKeystone("Y", .31, .51, .16);
  private final static ColorKeystone GREEN = new ColorKeystone("G", .22, .51, .26);
  private final static ColorKeystone BLUE = new ColorKeystone("B", .18, .43, .38);

  private final static ColorEntry RED_MAPPED = new ColorEntry(RED, BLUE);
  private final static ColorEntry YELLOW_MAPPED = new ColorEntry(YELLOW, GREEN);
  private final static ColorEntry GREEN_MAPPED = new ColorEntry(GREEN, YELLOW);
  private final static ColorEntry BLUE_MAPPED = new ColorEntry(BLUE, RED);

  private final static ColorEntry[] COLOR_MAP = { RED_MAPPED, YELLOW_MAPPED, GREEN_MAPPED, BLUE_MAPPED };

  public static class ColorEntry {
    public final ColorKeystone robotColor, actualColor;

    public ColorEntry(ColorKeystone robotColor, ColorKeystone actualColor) {
      this.robotColor = robotColor;
      this.actualColor = actualColor;
    }
  }

  private ColorKeystone(String identifier, double... rgb) {
    this.rgb = rgb;
    this.identifier = identifier;
  }

  private double getDifference(ColorKeystone compare) {
    return compare == null ? 3
        : Math.pow(rgb[0] - compare.rgb[0], 2) + Math.pow(rgb[1] - compare.rgb[1], 2)
            + Math.pow(rgb[2] - compare.rgb[2], 2);
  }

  public static ColorEntry getClosestColor(edu.wpi.first.wpilibj.util.Color currentColor) {
    ColorKeystone current = new ColorKeystone("n", currentColor.red, currentColor.green, currentColor.blue);
    ColorEntry min = null;
    for (ColorEntry entry : COLOR_MAP) {
      if (current.getDifference(entry.robotColor) < .1
          && (min == null || current.getDifference(entry.robotColor) < current.getDifference(min.robotColor)))
        min = entry;
    }
    return min;
  }

  public String getIdentifier() {
    return identifier;
  }
}