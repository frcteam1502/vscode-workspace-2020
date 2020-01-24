/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.ADXL345_I2C;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.interfaces.Accelerometer.Range;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import static com.revrobotics.CANSparkMaxLowLevel.MotorType.*;

import com.revrobotics.CANSparkMax;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants {

    public static final class Sensors {
        public static final ADXL345_I2C gyro = new ADXL345_I2C(Port.kOnboard, Range.k4G);
    }

    public static final class Motors {
        public static final CANSparkMax RANDOM_MOTOR = new CANSparkMax(1, kBrushless);
    }

    public static final class Joysticks {
        public static final XboxController XBOX = new XboxController(2);
        public static final Joystick LEFT_JOYSTICK = new Joystick(0);
        public static final Joystick RIGHT_JOYSTICK = new Joystick(1);
    }

    public static final class Buttons {
        public static final Button A = new JoystickButton(Joysticks.XBOX, 0);
        public static final Button B = new JoystickButton(Joysticks.XBOX, 1);
        public static final Button X = new JoystickButton(Joysticks.XBOX, 2);
        public static final Button Y = new JoystickButton(Joysticks.XBOX, 3);
        public static final Button LB = new JoystickButton(Joysticks.XBOX, 4);
        public static final Button RB = new JoystickButton(Joysticks.XBOX, 5);
        public static final Button START = new JoystickButton(Joysticks.XBOX, 6);
        public static final Button BACK = new JoystickButton(Joysticks.XBOX, 7);
    }
}
