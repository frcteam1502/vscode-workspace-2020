package frc.robot;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.ADXL345_I2C;
import static edu.wpi.first.wpilibj.I2C.Port.*;
import edu.wpi.first.wpilibj.interfaces.Accelerometer.Range;
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
        public static final Lidar BACK_LIDAR = new Lidar(new I2C(kMXP, 0x62), 0x62); // Do changed address first
        public static final Lidar FRONT_LIDAR = new Lidar(new I2C(kOnboard, 0x62), 0x62);
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
        public static final double convert = .0125; // ill rename it later
    }
}
