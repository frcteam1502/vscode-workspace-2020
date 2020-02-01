package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.ColorKeystone;

public class Spinner extends SubsystemBase {

  private final CANSparkMax spinner;
  private final ColorSensorV3 colorSensor;
  private int counter = 0;
  private ColorKeystone startColor = null;
  private ColorKeystone lastColor = null;

  public Spinner(CANSparkMax spinner, ColorSensorV3 colorSensor) {
    this.spinner = spinner;
    this.colorSensor = colorSensor;
  }

  /**
   * <b>Side Effects</b>
   * <ul>
   * <li>Sets start color as the color closest to the current color
   * <li>Sets the last color as the start color
   * </ul>
   */
  public void setColor() {
    this.startColor = getColor().getClosestColor();
    this.lastColor = startColor;
  }

  /**
   * @return instance of ColorKeystone with RGB values current being looked at
   */
  public ColorKeystone getColor() {
    return ColorKeystone.getInstance(colorSensor.getColor());
  }

  /**
   * Side effects
   * <ul>
   * <li>sets last color as current color if current color != last color
   * <li>increments counter if current == start color but != to last color
   * </ul>
   * 
   * @return Value of counter is less than 8
   */
  public boolean checkSpinner() {
    ColorKeystone current = getColor().getClosestColor();
    if (current != null || current != this.lastColor) {
      if (current == this.startColor)
        this.counter++;
      this.lastColor = current;
    }
    return this.counter >= 8;
  }

  public void setSpeed(double speed) {
    spinner.set(speed);
  }

  public void periodic() {
  }
}
