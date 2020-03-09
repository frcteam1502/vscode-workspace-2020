package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.Joysticks;
import frc.robot.Constants.Motors;
import frc.robot.Constants.Sensors;
import frc.robot.commands.Autonomous;
import frc.robot.commands.MoveSpinnerByEncoder;
import frc.robot.commands.ReduceOneBall;
import frc.robot.commands.RunBelt;
import frc.robot.commands.RunIntake;
import frc.robot.commands.SpinnerLiftDown;
import frc.robot.commands.SpinnerLiftUp;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.IntakeBelt;
import frc.robot.subsystems.Spinner;
import frc.robot.subsystems.SpinnerLift;

public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  SendableChooser<Autonomous.StartPosition> startPositionChooser = new SendableChooser<>();
  public final Drivetrain drivetrain = new Drivetrain(Motors.DRIVE_FRONT_LEFT, Motors.DRIVE_BACK_LEFT,
      Motors.DRIVE_FRONT_RIGHT, Motors.DRIVE_BACK_RIGHT);
  public final Intake intake = new Intake(Motors.INTAKE, Joysticks.XBOX);
  public final IntakeBelt belt = new IntakeBelt(Sensors.INFRARED, Motors.INTAKE_BELT_LEFT, Motors.INTAKE_BELT_RIGHT,
      Joysticks.XBOX);
  private final RunIntake intakeForward = new RunIntake(intake, -.4);
  private final RunIntake intakeBackward = new RunIntake(intake, .4);
  private final SpinnerLift spinnerLift = new SpinnerLift(Motors.SPINNER_LIFT);
  private final Spinner spinner = new Spinner(Sensors.COLOR_SENSOR, Motors.SPINNER_WHEEL);

  public RobotContainer() {
    configureButtonBindings();
    startPositionChooser.addOption("Left position", Autonomous.StartPosition.LEFT);
    startPositionChooser.setDefaultOption("Center position", Autonomous.StartPosition.CENTER);
    startPositionChooser.addOption("Right position", Autonomous.StartPosition.RIGHT);
    SmartDashboard.putData(startPositionChooser);
  }

  private void configureButtonBindings() {
    Joysticks.XBOX.DP_UP.whenHeld(new SpinnerLiftUp(spinnerLift));
    Joysticks.XBOX.DP_DOWN.whenHeld(new SpinnerLiftDown(spinnerLift));
    Joysticks.XBOX.Y.whenHeld(new MoveSpinnerByEncoder(spinner));
    Joysticks.XBOX.RB.toggleWhenPressed(intakeForward);
    Joysticks.XBOX.L3.toggleWhenPressed(intakeBackward);
    Joysticks.XBOX.L3.cancelWhenPressed(intakeForward);
    Joysticks.XBOX.RB.cancelWhenPressed(intakeBackward);
    Joysticks.XBOX.X.whenHeld(new RunBelt(belt));
    Joysticks.XBOX.A.whenPressed(new ReduceOneBall(belt));
    Joysticks.LEFT_JOYSTICK.TRIGGER.whenPressed(() -> Joysticks.RIGHT_JOYSTICK.toggleDirection());
    // Joysticks.LEFT_JOYSTICK.TRIGGER.whenHeld(new LidarStop(drivetrain,
    // () -> Constants.Joysticks.RIGHT_JOYSTICK.getY() > 0 ?
    // LidarStop.Direction.FORWARDS
    // : LidarStop.Direction.BACKWARDS,
    // () -> Constants.Joysticks.RIGHT_JOYSTICK.getY(), () ->
    // Constants.Joysticks.LEFT_JOYSTICK.getX()));
  }

  public Command getAutonomousCommand() {
    return new Autonomous(drivetrain, belt, Autonomous.StartPosition.LEFT);
  }
}
