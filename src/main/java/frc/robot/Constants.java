package frc.robot;

import static com.revrobotics.CANSparkMaxLowLevel.MotorType.kBrushed;
import static com.revrobotics.CANSparkMaxLowLevel.MotorType.kBrushless;
import static edu.wpi.first.wpilibj.I2C.Port.kMXP;
import static edu.wpi.first.wpilibj.I2C.Port.kOnboard;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.ADXL345_I2C;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.interfaces.Accelerometer.Range;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.DPadButton.Direction;

public final class Constants {

  public static final class Motors {
    public static final CANSparkMax SPINNER_LIFT_MOTOR = new CANSparkMax(1, kBrushless);
    public static final CANSparkMax SPINNER_MOTOR = new CANSparkMax(2, kBrushless);
    public static final CANSparkMax DRIVE_FRONT_LEFT = new CANSparkMax(3, kBrushed);
    public static final CANSparkMax DRIVE_BACK_LEFT = new CANSparkMax(4, kBrushed);
    public static final CANSparkMax DRIVE_FRONT_RIGHT = new CANSparkMax(5, kBrushed);
    public static final CANSparkMax DRIVE_BACK_RIGHT = new CANSparkMax(6, kBrushed);
  }

  public static class Sensors {
    public static final DigitalInput SPINNER_UPPER_LIFT_LIMIT = new DigitalInput(0);
    public static final DigitalInput SPINNER_LOWER_LIFT_LIMIT = new DigitalInput(1);
    public static final ColorSensorV3 COLOR_SENSOR = new ColorSensorV3(kOnboard);
    public static final Lidar LIDAR = new Lidar(kOnboard, 0x62);
    public static final ADXRS450_Gyro LIFT_GYRO = new ADXRS450_Gyro(Port.kOnboardCS0);
    // public static final I2C COLOR_SENSOR = new I2C(kOnboard, 0x39); //port, I2c
    // address
    public static final ADXL345_I2C GYRO = new ADXL345_I2C(kMXP, Range.k4G);
  }

  public static final class Joysticks {
    public static final XboxController XBOX = new XboxController(2);
    public static final Joystick LEFT_JOYSTICK = new Joystick(0);
    public static final Joystick RIGHT_JOYSTICK = new Joystick(1);
  }

  /**
   * I have no words.
   */
  public static final class Buttons {
    public static final Button DP_UP = new DPadButton(Joysticks.XBOX, Direction.Up);
    public static final Button DP_LEFT = new DPadButton(Joysticks.XBOX, Direction.Left);
    public static final Button DP_DOWN = new DPadButton(Joysticks.XBOX, Direction.Down);
    public static final Button DP_RIGHT = new DPadButton(Joysticks.XBOX, Direction.Right);
    public static final Button A = new JoystickButton(Joysticks.XBOX, 0);
    public static final Button B = new JoystickButton(Joysticks.XBOX, 1);
    public static final Button X = new JoystickButton(Joysticks.XBOX, 2);
    public static final Button Y = new JoystickButton(Joysticks.XBOX, 3);
    public static final Button LB = new JoystickButton(Joysticks.XBOX, 4);
    public static final Button RB = new JoystickButton(Joysticks.XBOX, 5);
    public static final Button START = new JoystickButton(Joysticks.XBOX, 6);
    public static final Button BACK = new JoystickButton(Joysticks.XBOX, 7);

    public static final Button LEFT_TRIGGER = new JoystickButton(Joysticks.LEFT_JOYSTICK, 1);
    public static final Button LEFT_THUMB = new JoystickButton(Joysticks.LEFT_JOYSTICK, 2);
    public static final Button LEFT_UPPER_THREE = new JoystickButton(Joysticks.LEFT_JOYSTICK, 3);
    public static final Button LEFT_UPEER_FOUR = new JoystickButton(Joysticks.LEFT_JOYSTICK, 4);
    public static final Button LEFT_UPPER_FIVE = new JoystickButton(Joysticks.LEFT_JOYSTICK, 5);
    public static final Button LEFT_UPPER_SIX = new JoystickButton(Joysticks.LEFT_JOYSTICK, 6);
    public static final Button LEFT_LOWER_SEVEN = new JoystickButton(Joysticks.LEFT_JOYSTICK, 7);
    public static final Button LEFT_LOWER_EIGHT = new JoystickButton(Joysticks.LEFT_JOYSTICK, 8);
    public static final Button LEFT_LOWER_NINE = new JoystickButton(Joysticks.LEFT_JOYSTICK, 9);
    public static final Button LEFT_LOWER_TEN = new JoystickButton(Joysticks.LEFT_JOYSTICK, 10);
    public static final Button LEFT_LOWER_ELEVEN = new JoystickButton(Joysticks.LEFT_JOYSTICK, 11);
    public static final Button LEFT_LOWER_TWELVE = new JoystickButton(Joysticks.LEFT_JOYSTICK, 12);

    public static final Button RIGHT_TRIGGER = new JoystickButton(Joysticks.LEFT_JOYSTICK, 1);
    public static final Button RIGHT_THUMB = new JoystickButton(Joysticks.LEFT_JOYSTICK, 2);
    public static final Button RIGHT_UPPER_THREE = new JoystickButton(Joysticks.LEFT_JOYSTICK, 3);
    public static final Button RIGHT_UPEER_FOUR = new JoystickButton(Joysticks.LEFT_JOYSTICK, 4);
    public static final Button RIGHT_UPPER_FIVE = new JoystickButton(Joysticks.LEFT_JOYSTICK, 5);
    public static final Button RIGHT_UPPER_SIX = new JoystickButton(Joysticks.LEFT_JOYSTICK, 6);
    public static final Button RIGHT_LOWER_SEVEN = new JoystickButton(Joysticks.LEFT_JOYSTICK, 7);
    public static final Button RIGHT_LOWER_EIGHT = new JoystickButton(Joysticks.LEFT_JOYSTICK, 8);
    public static final Button RIGHT_LOWER_NINE = new JoystickButton(Joysticks.LEFT_JOYSTICK, 9);
    public static final Button RIGHT_LOWER_TEN = new JoystickButton(Joysticks.LEFT_JOYSTICK, 10);
    public static final Button RIGHT_LOWER_ELEVEN = new JoystickButton(Joysticks.LEFT_JOYSTICK, 11);
    public static final Button RIGHT_LOWER_TWELVE = new JoystickButton(Joysticks.LEFT_JOYSTICK, 12);
  }
}
