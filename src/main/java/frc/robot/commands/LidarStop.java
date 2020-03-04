package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Lidar;
import frc.robot.PIDController;
import frc.robot.subsystems.Drivetrain;

public class LidarStop extends CommandBase {
  static final double TARGET_DISTANCE_FRONT = 60; // cm
  static final double TARGET_DISTANCE_BACK = 20;
  private Drivetrain drivetrain;
  private PIDController lidarStopController = new PIDController(4e-3, 0, 0);
  Supplier<Double> getIntendedVelocity;
  Supplier<Double> getTurn;
  Supplier<Direction> getInitialDirection;
  // 1 if forwards, -1 if backwards
  int directionMultiplier;
  Direction direction;

  public enum Direction {
    FORWARDS, BACKWARDS
  }

  public LidarStop(Drivetrain drivetrain, Supplier<Direction> getInitialDirection, Supplier<Double> getIntendedVelocity,
      Supplier<Double> getTurn) {
    addRequirements(drivetrain);
    this.drivetrain = drivetrain;
    this.getIntendedVelocity = getIntendedVelocity;
    this.getInitialDirection = getInitialDirection;
    this.getTurn = getTurn;
  }

  Lidar getLidar() {
    return direction == Direction.FORWARDS ? Constants.Sensors.FRONT_LIDAR : Constants.Sensors.BACK_LIDAR;
  }

  double getTargetDistance() {
    return direction == Direction.FORWARDS ? TARGET_DISTANCE_FRONT : TARGET_DISTANCE_BACK;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    lidarStopController.reset();
    direction = getInitialDirection.get();
    directionMultiplier = direction == Direction.FORWARDS ? 1 : -1;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double error = getLidar().getDistance() - getTargetDistance();
    double correction = directionMultiplier * lidarStopController.getCorrection(error);
    double intendedVelocity = getIntendedVelocity.get();
    double power = direction == Direction.FORWARDS ? Math.min(correction, intendedVelocity)
        : Math.max(correction, intendedVelocity);
    SmartDashboard.putString("LidarStop direction", direction.toString());
    SmartDashboard.putNumber("LidarStop error", error);
    SmartDashboard.putNumber("LidarStop correction", correction);
    SmartDashboard.putNumber("LidarStop intended velocity", intendedVelocity);
    double leftPower = power + getTurn.get();
    double rightPower = power - getTurn.get();
    double[] limitedPower = DriveByJoysticks.limitPower(leftPower, rightPower);
    leftPower = limitedPower[0];
    rightPower = limitedPower[1];
    drivetrain.move(leftPower, rightPower);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drivetrain.move(0, 0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}