package frc.robot;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.ADXL345_I2C;
import static edu.wpi.first.wpilibj.I2C.Port.*;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.interfaces.Accelerometer.Range;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.DPadButton.Direction;

import edu.wpi.first.wpilibj.SPI;

import static com.revrobotics.CANSparkMaxLowLevel.MotorType.*;

public final class Constants {

  public static final class Motors {
    public static final CANSparkMax DRIVE_FRONT_LEFT = new CANSparkMax(1, kBrushless);
    public static final CANSparkMax DRIVE_BACK_LEFT = new CANSparkMax(2, kBrushless);
    public static final CANSparkMax DRIVE_FRONT_RIGHT = new CANSparkMax(3, kBrushless);
    public static final CANSparkMax DRIVE_BACK_RIGHT = new CANSparkMax(4, kBrushless);
  }

  public static class Sensors {
    public static final DigitalInput SPINNER_LIFT_LIMIT = new DigitalInput(0);
    public static final ColorSensorV3 COLOR_SENSOR = new ColorSensorV3(kOnboard);
    public static final AHRS LIFT_GYRO = new AHRS(SPI.Port.kMXP);
    public static final ADXL345_I2C GYRO = new ADXL345_I2C(kMXP, Range.k4G);
    public static final Lidar LIDAR = new Lidar(new I2C(kOnboard, 0x62));
  }

  public static final class Joysticks {
    public static final XboxController XBOX = new XboxController(2);
    public static final Joystick LEFT_JOYSTICK = new Joystick(0);
    public static final Joystick RIGHT_JOYSTICK = new Joystick(1);
  }

  public static final class ConversionFactors {
    private static final double CENTIMETERS_PER_ENCODER_VALUE = 2.54 * 89.0 / 50.0;
    public static final double INCHES_PER_SECOND_PER_ENCODER_RPM = 48 / 1620;
    public static final double CENTIMETERS_PER_SECOND_PER_ENCODER_RPM = 122.0 / 1620.0;
    public static final double convert = .0125; // ill rename it later
  }

  public static final class Buttons {
    public static final Button DP_UP = new DPadButton(Joysticks.XBOX, Direction.Up);
    public static final Button DP_LEFT = new DPadButton(Joysticks.XBOX, Direction.Left);
    public static final Button DP_DOWN = new DPadButton(Joysticks.XBOX, Direction.Down);
    public static final Button DP_RIGHT = new DPadButton(Joysticks.XBOX, Direction.Right);
    public static final Button A = new JoystickButton(Joysticks.XBOX, 1);
    public static final Button B = new JoystickButton(Joysticks.XBOX, 2);
    public static final Button X = new JoystickButton(Joysticks.XBOX, 3);
    public static final Button Y = new JoystickButton(Joysticks.XBOX, 4);
    public static final Button LB = new JoystickButton(Joysticks.XBOX, 5);
    public static final Button RB = new JoystickButton(Joysticks.XBOX, 6);
    public static final Button START = new JoystickButton(Joysticks.XBOX, 7);
    public static final Button BACK = new JoystickButton(Joysticks.XBOX, 8);
  }
}
