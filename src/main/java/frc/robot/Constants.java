/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.I2C;

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

    public static class Motors {
        public static final CANSparkMax SPINNER_LIFT = new CANSparkMax(0, MotorType.kBrushed);
        public static final CANSparkMax SPIN_WHEEL = new CANSparkMax(1, MotorType.kBrushless);
    }

    public static class Sensors {
        public static final DigitalInput SPINNER_LIMIT = new DigitalInput(0);
        public static final ColorSensorV3 WHEEL_COLOR = new ColorSensorV3(I2C.Port.kMXP);
    }
}
