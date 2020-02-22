package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.PIDController;
import frc.robot.subsystems.Drivetrain;

public class LidarStop extends CommandBase {
  static final double TARGET_DISTANCE = 20; // cm
  private static final double STOPPING_TIME = 0.5;
  private Drivetrain drivetrain;
  private PIDController lidarStopController = new PIDController(4e-3, 0, 0);
  private boolean hasReachedStoppingDistance;
  private Supplier<Boolean> shouldFinish;
  private Supplier<Double> getSpeed;

  public LidarStop(Drivetrain drivetrain, Supplier<Boolean> shouldFinish, Supplier<Double> getSpeed) {
    addRequirements(drivetrain);
    this.drivetrain = drivetrain;
    this.shouldFinish = shouldFinish;
    this.getSpeed = getSpeed;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    hasReachedStoppingDistance = false;
    lidarStopController.reset();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (!hasReachedStoppingDistance && isBeyondStoppingDistance()) {
      hasReachedStoppingDistance = true;
    }
    if (hasReachedStoppingDistance) {
      double error = Constants.Sensors.BACK_LIDAR.getDistance() - TARGET_DISTANCE;
      double correction = lidarStopController.getCorrection(error);
      drivetrain.move(correction, correction);
    } else {
      double speed = getSpeed.get();
      drivetrain.move(speed, speed);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drivetrain.move(0, 0);
  }

  boolean isBeyondStoppingDistance() {
    double averageVel = (drivetrain.getLeftEncoderVelocity() + drivetrain.getRightEncoderVelocity()) / 2;
    double speedInCmPerSecond = averageVel * Constants.ConversionFactors.CENTIMETERS_PER_SECOND_PER_ENCODER_RPM;
    double distanceFromTarget = Constants.Sensors.BACK_LIDAR.getDistance() - TARGET_DISTANCE;
    double timeToReachDestination = distanceFromTarget / speedInCmPerSecond;
    return timeToReachDestination < STOPPING_TIME;
  }

  @Override
  public boolean isFinished() {
    return lidarStopController.isStable(2, 2000) || shouldFinish.get();
  }
}