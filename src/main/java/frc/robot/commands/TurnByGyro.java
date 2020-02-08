/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.PIDController;
import frc.robot.subsystems.Drivetrain;

public class TurnByGyro extends CommandBase {

  static final double DEGREE_TOLERANCE = 0.1;
  Drivetrain drivetrain;
  double startAngle;
  double targetAngle;
  double turnAngle;
  boolean isUsingPid = false;
  PIDController rotationController = new PIDController(6e-3, 2e-5, 5e-1);

  /**
   * Creates a new TurnByGyro.
   */
  public TurnByGyro(Drivetrain drivetrain, double turnAngle) {
    this.drivetrain = drivetrain;
    this.turnAngle = turnAngle;
    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // lag alert getAngle() 5s lag
    startAngle = Constants.Sensors.LIFT_GYRO.getAngle();
    targetAngle = startAngle + turnAngle;
    isUsingPid = false;
    rotationController.reset();
    SmartDashboard.putNumber("P", rotationController.P);
    SmartDashboard.putNumber("I", rotationController.I);
    SmartDashboard.putNumber("D", rotationController.D);
    if (turnAngle > 0) {
      drivetrain.move(0.1, -0.1);
    } else {
      drivetrain.move(-0.1, 0.1);
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // rotationController.P = SmartDashboard.getNumber("P", rotationController.P);
    // rotationController.I = SmartDashboard.getNumber("I", rotationController.I);
    // rotationController.D = SmartDashboard.getNumber("D", rotationController.D);
    double angle = Constants.Sensors.LIFT_GYRO.getAngle();
    SmartDashboard.putNumber("Error in angle", angle - targetAngle);
    SmartDashboard.putBoolean("PID activated", isUsingPid);
    // if the target angle has been passed, start using PID

    if (!isUsingPid && Math.abs(angle - startAngle) >= Math.abs(targetAngle - startAngle)) {
      isUsingPid = true;
    }
    if (isUsingPid) {
      rotationController.input(angle - targetAngle);
      double offset = rotationController.getCorrection();
      drivetrain.move(-offset, offset);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drivetrain.move(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    boolean isStable = rotationController.isStable(DEGREE_TOLERANCE, 1000);
    SmartDashboard.putBoolean("isStable", isStable);
    return isStable;
  }
}
