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
    startColor = ColorKeystone.getClosestColor(colorSensor.getColor());
    lastColor = startColor;
  }

  public boolean checkSpinner() {
    ColorKeystone current = ColorKeystone.getClosestColor(colorSensor.getColor());
    if (current != null || current != lastColor) {
      if (current == startColor)
        counter++;
      lastColor = current;
    }
    return counter >= 8;
  }

  public void setSpeed(double speed) {
    spinner.set(speed);
  }

  public void periodic() {
  }
}
