package frc.robot;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.DPadButton.Direction;

import static com.revrobotics.CANSparkMaxLowLevel.MotorType.*;

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
