/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Lift;

public class LiftUp extends CommandBase {
  private double target;
  private Lift subsystem;

  /**
   * Creates a new MoveLiftOneIncrement.
   */
  public LiftUp(Lift subsystem) {
    addRequirements(subsystem);
    this.subsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    SmartDashboard.putBoolean("init", true);
    SmartDashboard.putNumber("Target", target);
    subsystem.setEncoders(0);
    target = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (subsystem.getLeftEncoder() >= target && subsystem.getRightEncoder() <= -target) {
      target += subsystem.getCycleDistance();
      subsystem.setRight(-.02);
      subsystem.setLeft(.02);
    } else if (subsystem.getLeftEncoder() >= target && subsystem.getRightEncoder() > -target)
      subsystem.setLeft(0);
    else if (subsystem.getLeftEncoder() < target && subsystem.getRightEncoder() <= -target)
      subsystem.setRight(0);
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