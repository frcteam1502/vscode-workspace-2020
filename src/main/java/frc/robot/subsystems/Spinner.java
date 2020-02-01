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

  public void setColor() {
    this.startColor = getColor().getClosestColor();
    this.lastColor = startColor;
  }

  public ColorKeystone getColor() {
    return ColorKeystone.getInstance(colorSensor.getColor());
  }

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
