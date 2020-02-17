package frc.robot;

import static frc.robot.Constants.Sensors.BACK_LIDAR;
import static frc.robot.Constants.Sensors.FRONT_LIDAR;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.Joysticks;
import frc.robot.Constants.Motors;
import frc.robot.Constants.Sensors;
import frc.robot.commands.Autonomous;
import frc.robot.commands.LidarStop;
import frc.robot.subsystems.Drivetrain;

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
  public final Drivetrain drivetrain = new Drivetrain(Sensors.BACK_LIDAR, Sensors.FRONT_LIDAR, Motors.DRIVE_FRONT_LEFT,
      Motors.DRIVE_BACK_LEFT, Motors.DRIVE_FRONT_RIGHT, Motors.DRIVE_BACK_RIGHT);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    // BACK_LIDAR.changeAddress();
    // FRONT_LIDAR.changeAddress();
    // drivetrain = new Drivetrain(BACK_LIDAR, FRONT_LIDAR, Motors.DRIVE_FRONT_LEFT,
    // Motors.DRIVE_BACK_LEFT,
    // Motors.DRIVE_FRONT_RIGHT, Motors.DRIVE_BACK_RIGHT);
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    Joysticks.XBOX.A.whenPressed(() -> Constants.Joysticks.RIGHT_JOYSTICK.toggleDirection());
    Joysticks.XBOX.B.whenPressed(() -> {
      SmartDashboard.putBoolean("Back Only", BACK_LIDAR.addressOnly());
      SmartDashboard.putBoolean("Front Only", FRONT_LIDAR.addressOnly());
      SmartDashboard.putNumber("Back Address", BACK_LIDAR.readAddress());
      SmartDashboard.putNumber("Front Address", FRONT_LIDAR.readAddress());
    });
    Joysticks.LEFT_JOYSTICK.TRIGGER.whenHeld(
        new LidarStop(drivetrain, () -> Joysticks.RIGHT_JOYSTICK.getY() <= 0, () -> Joysticks.RIGHT_JOYSTICK.getY()));
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
