package frc.robot;

import static com.revrobotics.CANSparkMaxLowLevel.MotorType.kBrushed;
import static com.revrobotics.CANSparkMaxLowLevel.MotorType.kBrushless;
import static edu.wpi.first.wpilibj.I2C.Port.kOnboard;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SPI.Port;

public final class Constants {

  public static final class Motors {
    public static final CANSparkMax DRIVE_FRONT_LEFT = new CANSparkMax(1, kBrushless);
    public static final CANSparkMax DRIVE_BACK_LEFT = new CANSparkMax(2, kBrushless);
    public static final CANSparkMax DRIVE_FRONT_RIGHT = new CANSparkMax(3, kBrushless);
    public static final CANSparkMax DRIVE_BACK_RIGHT = new CANSparkMax(4, kBrushless);
    // TODO: these should be Victor SPXs
    public static final CANSparkMax INTAKE_BELT_LEFT = new CANSparkMax(11, kBrushed);
    public static final CANSparkMax INTAKE_BELT_RIGHT = new CANSparkMax(5, kBrushed);
    public static final CANSparkMax SPINNER_WHEEL = new CANSparkMax(15, kBrushless);
  }

  public static class Sensors {
    public static final DigitalInput INFRARED = new DigitalInput(0);
    public static final ColorSensorV3 COLOR_SENSOR = new ColorSensorV3(kOnboard);
    public static final AHRS GYRO = new AHRS(SPI.Port.kMXP);
    public static final Lidar BACK_LIDAR = new Lidar(new I2C(kOnboard, 0x62), 0x60); // Do changed address first
    public static final Lidar FRONT_LIDAR = new Lidar(new I2C(kOnboard, 0x62), 0x62);
    // public static final DigitalInput SPINNER_UPPER_LIFT_LIMIT = new
    // DigitalInput(0);
    // public static final DigitalInput SPINNER_LOWER_LIFT_LIMIT = new
    // DigitalInput(1);
    // public static final ADXRS450_Gyro LIFT_GYRO = new
    // ADXRS450_Gyro(Port.kOnboardCS0);
  }

  public static final class Joysticks {
    public static final XBox XBOX = new XBox(2);
    public static final Joystick LEFT_JOYSTICK = new Joystick(1);
    public static final Joystick RIGHT_JOYSTICK = new Joystick(0);
  }

  public static final class ConversionFactors {
    public static final double CENTIMETERS_PER_ENCODER_VALUE = 2.54 * 89.0 / 50.0;
    public static final double INCHES_PER_SECOND_PER_ENCODER_RPM = 48 / 1620;
    public static final double CENTIMETERS_PER_SECOND_PER_ENCODER_RPM = 122.0 / 1620.0;
    public static final double ENCODERS_PER_SPINNER_ROTATION = 97.3893722613 / 25.132741;
  }
}
