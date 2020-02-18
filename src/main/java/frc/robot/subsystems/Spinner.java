package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.ColorKeystone;

public class Spinner extends SubsystemBase {

  CANSparkMax spinWheel;
  double wheelSpeed = 1;
  ColorSensorV3 colorSensor;
  int colorCount = 0;
  int rotationCount = 0;
  Color startColor = null;
  Color lastColor = null;
  String gameData = DriverStation.getInstance().getGameSpecificMessage();
  final int colorsPerRotation = 8;
  final int totalOfRotations = 3;
  final double differenceSumThreshhold = 0.1;
  private final Color RED = new Color(.602539, .300049, .097412);
  private final Color GREEN = new Color(.421875, .472656, .105469);
  private final Color BLUE = new Color(.234863, .528564, .236572);
  private final Color YELLOW = new Color(.184814, .422119, .393066);

  public Spinner(CANSparkMax spinWheel, ColorSensorV3 colorSensor) {
    this.spinWheel = spinWheel;
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
        rotationCount++;
      lastColor = current;
    }
    return rotationCount >= 8;
  }

  public Color getColorDifference(Color color1, Color color2) {
    double redDifference = color1.red - color2.red;
    double greenDifference = color1.green - color2.green;
    double blueDifference = color1.blue - color2.blue;

    Color colorDifference = new Color(redDifference, greenDifference, blueDifference);
    return colorDifference;
  }

  public Color gameInfo() {
    try {
      switch (gameData.charAt(0)) {
      case 'B':
        // Blue case code
        return BLUE;
      case 'G':
        // Green case
        return GREEN;
      case 'R':
        // Red case code
        return RED;
      case 'Y':
        // Yellow case code
        return YELLOW;
      default:
        // This is corrupt data
        return RED;
      }
    } catch (StringIndexOutOfBoundsException e) {
      SmartDashboard.putString("Big boi error", e.getMessage());
      return RED;
    }
  }

  public void periodic() {
  }
}
