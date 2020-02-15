package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Spinner extends SubsystemBase {

  CANSparkMax spinWheel;
  double wheelSpeed = 1;
  ColorSensorV3 wheelColor;
  int colorCount = 0;
  int rotationCount = 0;
  Color lastColor = null;
  String gameData = DriverStation.getInstance().getGameSpecificMessage();
  final int colorsPerRotation = 8;
  final int totalOfRotations = 3;
  final double diffrenceSumThreshhold = 0.1;
  private final Color RED = new Color(.602539, .300049, .097412);
  private final Color GREEN = new Color(.421875, .472656, .105469);
  private final Color BLUE = new Color(.234863, .528564, .236572);
  private final Color YELLOW = new Color(.184814, .422119, .393066);
  private final Color BLACK = new Color(0, 0, 0);

  public Spinner(CANSparkMax spinWheel, ColorSensorV3 wheelColor) {
    this.spinWheel = spinWheel;
    this.wheelColor = wheelColor;
  }
  /*
   * public void SpinLift(double placeholder1) { spinLift.set(placeholder1); }
   * 
   * public void SpinWheel(double placeholder2) { spinWheel.set(placeholder2); }
   */

  public void runSpin() {

    if (lastColor == null) {
      lastColor = wheelColor.getColor();
      spinWheel.set(wheelSpeed);
    } else if (lastColor != null) {
      Color currentColor = wheelColor.getColor();
      Color difColor = getColorDiffernce(lastColor, currentColor);
      double dif = difColor.red + difColor.blue + difColor.green;
      if (dif >= diffrenceSumThreshhold) {
        colorCount++;
        if (colorCount % colorsPerRotation == 0) {
          wheelSpeed = wheelSpeed - .15;
          spinWheel.set(wheelSpeed);
          rotationCount++;
        }
      }
      lastColor = currentColor;
    }
  }

  /*
   * CANEncoder enc = spinWheel.getEncoder(); if (enc.getPosition() < 10000)
   * spinWheel.set(1); else spinWheel.set(0);
   */

  public void compareColor(Color color, Color currentColor) {
    Color difColor = getColorDiffernce(color, currentColor);
    double dif = difColor.red + difColor.blue + difColor.green;
    if (dif >= diffrenceSumThreshhold) {
      spinWheel.set(.5);
    } else {
      spinWheel.set(0);
    }

  }

  public Color getColorDiffernce(Color color1, Color color2) {
    double color1Red = color1.red;
    double color1Green = color1.green;
    double color1Blue = color1.blue;
    double color2Red = color2.red;
    double color2Green = color2.green;
    double color2Blue = color2.blue;

    double redDifference = color1Red - color2Red;
    double greenDiffernce = color1Green - color2Green;
    double blueDiffernce = color1Blue - color2Blue;

    Color colorDiffernce = new Color(redDifference, greenDiffernce, blueDiffernce);
    return colorDiffernce;
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
