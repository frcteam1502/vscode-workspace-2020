package frc.robot;

import static com.revrobotics.CANSparkMaxLowLevel.MotorType.kBrushed;
import static com.revrobotics.CANSparkMaxLowLevel.MotorType.kBrushless;
import static edu.wpi.first.wpilibj.I2C.Port.*;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SPI;

public final class Constants {

  public static final class Motors {
    public static final CANSparkMax DRIVE_BACK_LEFT = new CANSparkMax(1, kBrushless);
    public static final CANSparkMax CLIMB_LEFT = new CANSparkMax(2, kBrushless);
    public static final VictorSPX BUDDY_LIFT = new VictorSPX(3);
    public static final CANSparkMax INTAKE_BELT_LEFT = new CANSparkMax(5, kBrushless);
    public static final CANSparkMax SLIDEY_BOI = new CANSparkMax(7, kBrushless);
    public static final CANSparkMax SPINNER_WHEEL = new CANSparkMax(8, kBrushless);
    public static final CANSparkMax SPINNER_LIFT = new CANSparkMax(9, kBrushed);
    public static final CANSparkMax DRIVE_FRONT_LEFT = new CANSparkMax(10, kBrushless);
    public static final CANSparkMax INTAKE_BELT_RIGHT = new CANSparkMax(11, kBrushless);
    public static final VictorSPX INTAKE = new VictorSPX(12);
    public static final CANSparkMax CLIMB_RIGHT = new CANSparkMax(13, kBrushless);
    public static final CANSparkMax DRIVE_BACK_RIGHT = new CANSparkMax(14, kBrushless);
    public static final CANSparkMax DRIVE_FRONT_RIGHT = new CANSparkMax(15, kBrushless);

  }

  public static class Sensors {
    public static final DigitalInput AUTO_SELECT_RIGHT = new DigitalInput(4); // Defined as LOW when position is
                                                                              // activated
    public static final DigitalInput AUTO_SELECT_LEFT = new DigitalInput(5);
    public static final DigitalInput INFRARED = new DigitalInput(1);
    public static final ColorSensorV3 COLOR_SENSOR = new ColorSensorV3(kOnboard);
    public static final AHRS GYRO = new AHRS(SPI.Port.kMXP);
    public static final Lidar FRONT_LIDAR = new Lidar(new I2C(kOnboard, 0x62), 0x62); // Do changed address first
    public static final Lidar BACK_LIDAR = new Lidar(new I2C(kMXP, 0x62), 0x62);
    // public static final DigitalInput LIFT_UPPER_LIMIT = new DigitalInput(0);
    // public static final DigitalInput LIFT_LOWER_LIMIT = new DigitalInput(1);
    // public static final DigitalInput INTAKE_INFRARED = new DigitalInput(2);
    // public static final DigitalInput SPINNER_UPPER_LIFT_LIMIT = new
    // DigitalInput(0);
    // public static final DigitalInput SPINNER_LOWER_LIFT_LIMIT = new
    // DigitalInput(1);
    // public static final ADXRS450_Gyro LIFT_GYRO = new
    // ADXRS450_Gyro(SPI.Port.kOnboardCS0);
  }

  public static final class Joysticks {
    public static final XBox XBOX = new XBox(2);
    public static final Joystick LEFT_JOYSTICK = new Joystick(1);
    public static final Joystick RIGHT_JOYSTICK = new Joystick(0);
  }

  public static final class ConversionFactors {
    public static final double INCHES_PER_ENCODER_VALUE = 3.9; // approximate (hotfix for real robot)
    public static final double CENTIMETERS_PER_ENCODER_VALUE = 2.54 * INCHES_PER_ENCODER_VALUE;
    public static final double INCHES_PER_SECOND_PER_ENCODER_RPM = 0.0611;
    public static final double CENTIMETERS_PER_SECOND_PER_ENCODER_RPM = 2.54 * INCHES_PER_SECOND_PER_ENCODER_RPM;
    public static final double ENCODERS_PER_SPINNER_ROTATION = 97.3893722613 / 25.132741;
    public static final double ENC_VALUES_PER_BALL = 6.2;
  }
}
