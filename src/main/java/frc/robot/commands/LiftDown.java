/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.BinaryOperator;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Lift;

public class LiftDown extends CommandBase {
  private double target;
  private Lift subsystem;
  private BinaryOperator<Double> average = (a, b) -> (a + b) / 2;;

  /**
   * Creates a new MoveLiftOneIncrement.
   */
  public LiftDown(Lift subsystem) {
    addRequirements(subsystem);
    this.subsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    subsystem.setEncoders(0);
    target = average.apply(subsystem.getLeftEncoder(), subsystem.getRightEncoder());
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putNumber("Left enc", subsystem.getLeftEncoder());
    SmartDashboard.putNumber("Right enc", subsystem.getRightEncoder());
    SmartDashboard.putNumber("Target", target);
    // if (subsystem.getLeftEncoder() >= target && subsystem.getRightEncoder() <=
    // -target) {
    // target -= subsystem.getCycleDistance();
    // subsystem.setRight(.15);
    // subsystem.setLeft(-.15);
    // } else if (subsystem.getLeftEncoder() <= target &&
    // subsystem.getRightEncoder() < -target)
    // subsystem.setLeft(0);
    // else if (subsystem.getLeftEncoder() > target && subsystem.getRightEncoder()
    // >= -target)
    // subsystem.setRight(0);
    subsystem.setRight(.15);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    subsystem.setLift(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
