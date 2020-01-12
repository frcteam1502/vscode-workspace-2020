package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    public static final class Motors {
        public static final int DRIVE_FRONT_LEFT = 1;
        public static final int DRIVE_BACK_LEFT = 2;
        public static final int DRIVE_FRONT_RIGHT = 3;
        public static final int DRIVE_BACK_RIGHT = 4;
    }

    public static final class Joysticks {
        public static final int LEFT_JOYSTICK = 0;
        public static final int RIGHT_JOYSTICK = 1;
        public static final int XBOX = 2;

    }
}
