/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeBelt;

public class RunIntakeBeltByTime extends CommandBase {
  double seconds;
  double startTimeMillis;
  IntakeBelt intakeBelt;

  /**
   * Creates a new RunIntakeByTime.
   * 
   * @param seconds Time in seconds
   */
  public RunIntakeBeltByTime(IntakeBelt intakeBelt, double seconds) {
    this.intakeBelt = intakeBelt;
    this.seconds = seconds;
    addRequirements(intakeBelt);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    startTimeMillis = System.currentTimeMillis();
    intakeBelt.runBelt(-0.75);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intakeBelt.runBelt(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    double ms = seconds * 1000;
    return System.currentTimeMillis() - startTimeMillis > ms;
  }
}
