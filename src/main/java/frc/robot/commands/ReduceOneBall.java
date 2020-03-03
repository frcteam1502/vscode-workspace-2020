/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.IntakeBelt;

public class ReduceOneBall extends CommandBase {
  private IntakeBelt subsystem;
  private double leftPosition;
  private double rightPosition;

  /**
   * Creates a new ReduceOneBall.
   */
  public ReduceOneBall(IntakeBelt subsystem) {
    addRequirements(subsystem);
    this.subsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    leftPosition = subsystem.getLeftPosition();
    rightPosition = subsystem.getRightPosition();
    subsystem.setLeftRumble(1);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (!isFinished())
      subsystem.runBelt(.1);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    subsystem.incrementBalls();
    subsystem.runBelt(0);
    subsystem.setLeftRumble(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    boolean rightIsDone = subsystem.getRightPosition()
        + Constants.ConversionFactors.ENC_VALUES_PER_BALL <= rightPosition;
    boolean leftIsDone = subsystem.getLeftPosition() - Constants.ConversionFactors.ENC_VALUES_PER_BALL >= leftPosition;

    return rightIsDone && leftIsDone;
  }
}
