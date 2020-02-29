package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.Joysticks;
import frc.robot.Constants.Motors;
import frc.robot.Constants.Sensors;
import frc.robot.commands.Autonomous;
import frc.robot.commands.MoveBeltOneBall;
import frc.robot.commands.RunBelt;
import frc.robot.commands.RunIntake;
import frc.robot.subsystems.BuddyLift;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.IntakeBelt;

public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  private final Autonomous autonCommands = new Autonomous();
  public final Drivetrain drivetrain = new Drivetrain(Sensors.BACK_LIDAR, /* Sensors.FRONT_LIDAR, */
      Motors.DRIVE_FRONT_LEFT, Motors.DRIVE_BACK_LEFT, Motors.DRIVE_FRONT_RIGHT, Motors.DRIVE_BACK_RIGHT);

  // public final Spinner spinner = new Spinner(Sensors.COLOR_SENSOR,
  // Motors.SPINNER_WHEEL);
  // public final SpinnerLift spinnerLift = new
  // SpinnerLift(Sensors.SPINNER_UPPER_LIFT_LIMIT,
  // Sensors.SPINNER_LOWER_LIFT_LIMIT, Motors.SPINNER_LIFT);
  public final Intake intake = new Intake(Motors.INTAKE);
  public final IntakeBelt belt = new IntakeBelt(Sensors.INFRARED, Motors.INTAKE_BELT_LEFT, Motors.INTAKE_BELT_RIGHT);
  private final RunIntake intakeForward = new RunIntake(intake, -.2);
  private final RunIntake intakeBackward = new RunIntake(intake, .2);
  // public final IntakeBelt belt = new IntakeBelt(Sensors.INTAKE_INFRARED,
  // Motors.INTAKE_BELT_LEFT,
  // Motors.INTAKE_BELT_RIGHT);
  // public final Lift climb = new Lift(Motors.CLIMB_RIGHT, Motors.CLIMB_LEFT,
  // Sensors.LIFT_UPPER_LIMIT,
  // Sensors.LIFT_LOWER_LIMIT);
  // public final LiftAdjust liftAdjust = new LiftAdjust(Motors.SLIDEY_BOI,
  // Sensors.LIFT_GYRO);
  public final BuddyLift buddy = new BuddyLift(Motors.BUDDY_LIFT);

  public RobotContainer() {
    configureButtonBindings();
  }

  private void configureButtonBindings() {
    // Joysticks.LEFT_JOYSTICK.TRIGGER.whenPressed(new InstantCommand(() -> {
    // SmartDashboard.putNumber("trigger press", Math.random());
    // }).andThen(
    // new LidarStop(drivetrain, () -> Joysticks.RIGHT_JOYSTICK.getY() <= 0, () ->
    // Joysticks.RIGHT_JOYSTICK.getY())));
    // Joysticks.XBOX.DP_DOWN.whileHeld(new LiftDown(climb));
    // Joysticks.XBOX.DP_UP.whileHeld(new LiftUp(climb));
    // Joysticks.XBOX.DP_RIGHT.whileHeld(new LiftManualAdjust(liftAdjust, 1));
    // Joysticks.XBOX.DP_LEFT.whileHeld(new LiftManualAdjust(liftAdjust, -1));
    // Joysticks.XBOX.LB.toggleWhenPressed(new RunBelt(belt));
    Joysticks.XBOX.RB.toggleWhenPressed(intakeForward);
    Joysticks.XBOX.L3.toggleWhenPressed(intakeBackward);
    Joysticks.XBOX.L3.cancelWhenPressed(intakeForward);
    Joysticks.XBOX.RB.cancelWhenPressed(intakeBackward);
    Joysticks.XBOX.A.whenPressed(new MoveBeltOneBall(belt));
    Joysticks.XBOX.X.whenHeld(new RunBelt(belt));
    // Joysticks.XBOX.A.whenPressed(
    // new SpinnerLiftUp(spinnerLift).andThen(new MoveTo(spinner)).andThen(new
    // SpinnerLiftDown(spinnerLift)));
    // Joysticks.XBOX.B.whenPressed(new SpinnerLiftUp(spinnerLift).andThen(new
    // MoveSpinnerByEncoder(spinner))
    // .andThen(new SpinnerLiftDown(spinnerLift)));
    // Joysticks.RIGHT_JOYSTICK.LOWER_SEVEN.and(Joysticks.LEFT_JOYSTICK.LOWER_SEVEN)
    // .whenActive(new RetractBuddyLiftPins(buddy));
    // Joysticks.XBOX.RB.toggleWhenPressed(new RunIntake(intake, -1));
    // Joysticks.XBOX.LB.toggleWhenPressed(new RunIntake(intake, 1));
  }

  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return autonCommands;
  }
}
