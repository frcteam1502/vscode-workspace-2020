/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SpinnerLift;

public class SpinnerLiftDown extends CommandBase {
  SpinnerLift subsystem;

  public SpinnerLiftDown(SpinnerLift subsystem) {
    addRequirements(subsystem);
    this.subsystem = subsystem;
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    subsystem.setLift(-.1);
  }

  @Override
  public void end(boolean interrupted) {
    subsystem.setLift(0);
  }

  @Override
  public boolean isFinished() {
    return subsystem.getLowerLimit();
  }
}
