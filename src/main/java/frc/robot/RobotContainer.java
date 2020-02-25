package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.Joysticks;
import frc.robot.Constants.Motors;
import frc.robot.Constants.Sensors;
import frc.robot.commands.Autonomous;
import frc.robot.commands.LidarStop;
import frc.robot.commands.LiftDown;
import frc.robot.commands.LiftManualAdjust;
import frc.robot.commands.LiftUp;
import frc.robot.commands.MoveSpinnerByEncoder;
import frc.robot.commands.MoveTo;
import frc.robot.commands.RunBelt;
import frc.robot.commands.RunIntake;
import frc.robot.commands.SpinnerLiftDown;
import frc.robot.commands.SpinnerLiftUp;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.IntakeBelt;
import frc.robot.subsystems.Lift;
import frc.robot.subsystems.LiftAdjust;
import frc.robot.subsystems.Spinner;
import frc.robot.subsystems.SpinnerLift;

public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  private final Autonomous autonCommands = new Autonomous();
  public final Drivetrain drivetrain = new Drivetrain(Sensors.BACK_LIDAR, Sensors.FRONT_LIDAR, Motors.DRIVE_FRONT_LEFT,
      Motors.DRIVE_BACK_LEFT, Motors.DRIVE_FRONT_RIGHT, Motors.DRIVE_BACK_RIGHT);

  public final Spinner spinner = new Spinner(Sensors.COLOR_SENSOR, Motors.SPINNER_WHEEL);
  public final SpinnerLift spinnerLift = new SpinnerLift(Sensors.SPINNER_UPPER_LIFT_LIMIT,
      Sensors.SPINNER_LOWER_LIFT_LIMIT, Motors.SPINNER_LIFT);
  public final Intake intake = new Intake(Motors.INTAKE);
  public final IntakeBelt belt = new IntakeBelt(Sensors.INTAKE_INFRARED, Motors.INTAKE_BELT_LEFT,
      Motors.INTAKE_BELT_RIGHT);
  public final Lift lift = new Lift(Motors.LIFT_RIGHT, Motors.LIFT_LEFT, Sensors.LIFT_UPPER_LIMIT,
      Sensors.LIFT_LOWER_LIMIT);
  public final LiftAdjust liftAdjust = new LiftAdjust(Motors.LIFT_ADJUSTER, Sensors.LIFT_GYRO);

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
    Joysticks.XBOX.DP_DOWN.whileHeld(new LiftDown(lift));
    Joysticks.XBOX.DP_UP.whileHeld(new LiftUp(lift));
    Joysticks.XBOX.DP_RIGHT.whileHeld(new LiftManualAdjust(liftAdjust, 1));
    Joysticks.XBOX.DP_LEFT.whileHeld(new LiftManualAdjust(liftAdjust, -1));
    Joysticks.XBOX.LB.toggleWhenPressed(new RunBelt(belt));
    Joysticks.XBOX.RB.whenPressed(new RunIntake(intake, 1));
    Joysticks.XBOX.L3.whenPressed(new RunIntake(intake, -1));
    Joysticks.XBOX.A.whenPressed(
        new SpinnerLiftUp(spinnerLift).andThen(new MoveTo(spinner)).andThen(new SpinnerLiftDown(spinnerLift)));
    Joysticks.XBOX.B.whenPressed(new SpinnerLiftUp(spinnerLift).andThen(new MoveSpinnerByEncoder(spinner))
        .andThen(new SpinnerLiftDown(spinnerLift)));
    // Joysticks.XBOX.RB.toggleWhenPressed(new RunIntake(intake, -1));
    // Joysticks.XBOX.LB.toggleWhenPressed(new RunIntake(intake, 1));
  }

  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return autonCommands;
  }
}
