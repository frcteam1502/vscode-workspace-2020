package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.Joysticks;
import frc.robot.Constants.Motors;
import frc.robot.Constants.Sensors;
import frc.robot.commands.Autonomous;
import frc.robot.commands.LidarStop;
import frc.robot.commands.MoveSpinnerByEncoder;
import frc.robot.commands.MoveTo;
import frc.robot.commands.RunIntake;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Spinner;

public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  private final Autonomous autonCommands = new Autonomous();
  public final Drivetrain drivetrain = new Drivetrain(Sensors.BACK_LIDAR, Sensors.FRONT_LIDAR, Motors.DRIVE_FRONT_LEFT,
      Motors.DRIVE_BACK_LEFT, Motors.DRIVE_FRONT_RIGHT, Motors.DRIVE_BACK_RIGHT);

  public final Spinner spinner = new Spinner(Sensors.COLOR_SENSOR, Motors.SPINNER_WHEEL);
  public final Intake intake = new Intake(Motors.INTAKE);

  public RobotContainer() {
    configureButtonBindings();
  }

  private void configureButtonBindings() {
    // Joysticks.LEFT_JOYSTICK.TRIGGER.whenPressed(new InstantCommand(() -> {
    // SmartDashboard.putNumber("trigger press", Math.random());
    // }).andThen(
    // new LidarStop(drivetrain, () -> Joysticks.RIGHT_JOYSTICK.getY() <= 0, () ->
    // Joysticks.RIGHT_JOYSTICK.getY())));
    Joysticks.XBOX.A.toggleWhenPressed(new MoveSpinnerByEncoder(spinner));
    Joysticks.XBOX.B.toggleWhenPressed(new MoveTo(spinner));
    // Joysticks.XBOX.RB.toggleWhenPressed(new RunIntake(intake, -1));
    // Joysticks.XBOX.LB.toggleWhenPressed(new RunIntake(intake, 1));
  }

  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return autonCommands;
  }
}
