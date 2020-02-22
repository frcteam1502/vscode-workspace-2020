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
  }

  @Override
  public void execute() {
    if (subsystem.isBroken())
      subsystem.runBelt(1);
    else
      subsystem.runBelt(0);
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
