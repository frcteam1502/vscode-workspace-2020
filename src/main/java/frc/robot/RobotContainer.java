package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.Autonomous;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  private final Autonomous autonCommands = new Autonomous();
  // private final Drivetrain driveTrain = new Drivetrain(Joysticks.leftJoystick,
  // Joysticks.rightJoystick,
  // new CANSparkMax(Motors.DRIVE_FRONT_LEFT, kBrushed),
  // new CANSparkMax(Motors.DRIVE_BACK_LEFT, kBrushed),
  // new CANSparkMax(Motors.DRIVE_FRONT_RIGHT, kBrushed),
  // new CANSparkMax(Motors.DRIVE_BACK_RIGHT, kBrushed));../.

  // public static final SpinnerLift spinnerLift = new
  // SpinnerLift(Sensors.SPINNER_LOWER_LIFT_LIMIT,
  // Sensors.SPINNER_UPPER_LIFT_LIMIT, Motors.SPINNER_LIFT_MOTOR);
  // public static final SpinnerUpCommand spinnerLiftUp = new
  // SpinnerUpCommand(spinnerLift);
  // public static final SpinnerDownCommands spinnerLiftDown = new
  // SpinnerDownCommands(spinnerLift);

  // public static final Spinner spinner = new Spinner(Sensors.COLOR_SENSOR,
  // Motors.SPINNER_MOTOR);
  // public static final SpinCommand spinnerCommands = new SpinCommand(spinner);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // Buttons.BACK.whenPressed(spinnerLiftUp.andThen(spinnerCommands).andThen(spinnerLiftDown));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return autonCommands;
  }
}
