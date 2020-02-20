package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.ColorKeystone;

public class Spinner extends SubsystemBase {

  private final ColorSensorV3 colorSensor;
  private final CANSparkMax wheel;

  public Spinner(ColorSensorV3 colorSensor, CANSparkMax wheel) {
    this.colorSensor = colorSensor;
    this.wheel = wheel;
  }

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
