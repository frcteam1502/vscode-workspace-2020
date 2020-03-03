package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeBelt;

public class RunBelt extends CommandBase {
  private IntakeBelt subsystem;

  public RunBelt(IntakeBelt subsystem) {
    addRequirements(subsystem);
    this.subsystem = subsystem;
  }

  @Override
  public void initialize() {
    subsystem.setLeftRumble(1);
  }

  @Override
  public void execute() {
    subsystem.runBelt(-1);
  }

  @Override
  public void end(boolean interrupted) {
    subsystem.runBelt(0);
    subsystem.setLeftRumble(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
