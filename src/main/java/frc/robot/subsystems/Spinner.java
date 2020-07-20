package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Spinner extends SubsystemBase {
  private final ColorSensorV3 colorSensor;
  private final CANSparkMax wheel;

  public Spinner(ColorSensorV3 colorSensor, CANSparkMax wheel) {
    this.colorSensor = colorSensor;
    this.wheel = wheel;
  }

  public enum ColorKeystone {
    // Confidential, Scientifically tested rgb values. Paten Pending
    RED("R", .4, .4, .2),
    YELLOW("Y", .31, .51, .16),
    GREEN("G", .22, .51, .26),
    BLUE("B", .18, .43, .38);
    
    // SHOULD BE A CHAR, BUT THEY SEND US A STRING AND IT LOOKS NICER WITHOUT CASTING
    private String identifier;
    private double red;
    private double green;
    private double blue;
  
    private ColorKeystone(String identifier, double red, double green, double blue) {
      this.red = red;
      this.green = green;
      this.blue = blue;
      this.identifier = identifier;
    }
  
    // rather random equation to find comparative difference.
    // Not "Random" but not rigorous
    // "Why 3?" Because i said so. And because the maximum difference a single
    // component of a color can be is 1, and since theres 3 components.
    private double getDifference(Color compare) {
      return compare == null ? 3
          : Math.pow(red - compare.red, 2) + Math.pow(green - compare.green, 2)
              + Math.pow(blue - compare.blue, 2);
    }
  
    public static ColorKeystone getClosestColor(Color currentColor) {
      ColorKeystone min = null;
      for (ColorKeystone entry : values()) {
        if (entry.getDifference(currentColor) < .1
            && (min == null || entry.getDifference(currentColor) < min.getDifference(currentColor)))
          min = entry;
      }
      // Returns the correct color accounting for the offset
      // The color sensor that is requesting the color is not looking at the same color the robot is
      // Offset might be incorrect, check before using
      return ColorKeystone.values()[(min.ordinal() + 2) % values().length];
    }
  
    // encapsulation
    public String getIdentifier() {
      return identifier;
    }
  }

  // dont know if this is necessary, but dont want to find out it is
  public void init() {
    wheel.getEncoder().setPosition(0);
  }

  public double getPosition() {
    return wheel.getEncoder().getPosition();
  }

  public boolean checkSpinner(String gameData) {
    return gameData == ColorKeystone.getClosestColor(colorSensor.getColor()).getIdentifier();
  }

  public void setSpeed(double speed) {
    wheel.set(speed);
  }

  public void periodic() {
  }
}
