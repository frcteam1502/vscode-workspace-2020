/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Drivetrain;

public class Autonomous extends SequentialCommandGroup {
  /**
   * Creates a new Autonomous.
   */
  public Autonomous(Drivetrain drivetrain) {
    addCommands(new DriveByDistance(drivetrain, 4 * 12));
    addCommands(new TurnByGyro(drivetrain, 90));
    addCommands(new DriveByDistance(drivetrain, 8 * 12));
    addCommands(new TurnByGyro(drivetrain, -90));
    addCommands(new DriveToWall(drivetrain));

    // (at 0.1 power)
    // 50 ticks = 89in
    // 100 ticks = 177in, 177.5in
  }
}
