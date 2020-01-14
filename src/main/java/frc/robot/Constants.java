package frc.robot;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.DPadButton.Direction;

import static com.revrobotics.CANSparkMaxLowLevel.MotorType.*;

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

    public static final class Motors {
        public static final CANSparkMax RANDOM_MOTOR = new CANSparkMax(1, kBrushless);
    }

    public static final class Joysticks {
        public static final XboxController XBOX = new XboxController(2);
        public static final Joystick LEFT_JOYSTICK = new Joystick(0);
        public static final Joystick RIGHT_JOYSTICK = new Joystick(1);
    }

    public static final class Buttons {
        public static final Button dpUp = new DPadButton(Joysticks.XBOX, Direction.Up);
        public static final Button dpLeft = new DPadButton(Joysticks.XBOX, Direction.Left);
        public static final Button dpDown = new DPadButton(Joysticks.XBOX, Direction.Down);
        public static final Button dpRight = new DPadButton(Joysticks.XBOX, Direction.Right);
        public static final Button back = new JoystickButton(Joysticks.XBOX, 7);
    }
}
