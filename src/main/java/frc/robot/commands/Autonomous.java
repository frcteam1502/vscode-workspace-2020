/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.Sensors;
import frc.robot.subsystems.Drivetrain;

public class Autonomous extends SequentialCommandGroup {
  public enum StartPosition {
    LEFT, CENTER, RIGHT
  }

  /**
   * Creates a new Autonomous.
   */
  public Autonomous(Drivetrain drivetrain, StartPosition startPosition) {
    if (startPosition == StartPosition.LEFT) {
      addCommands(new DriveByDistance(drivetrain, 4 * 12));
      addCommands(new TurnByGyro(drivetrain, 90));
      addCommands(new DriveByDistance(drivetrain, 8 * 12));
      addCommands(new TurnByGyro(drivetrain, -90));
    } else if (startPosition == StartPosition.RIGHT) {
      addCommands(new DriveByDistance(drivetrain, 4 * 12));
      addCommands(new TurnByGyro(drivetrain, -90));
      addCommands(new DriveByDistance(drivetrain, 8 * 12));
      addCommands(new TurnByGyro(drivetrain, 90));
    }
    addCommands(new DriveToWall(drivetrain, Sensors.FRONT_LIDAR));

    // (at 0.1 power)
    // 50 ticks = 89in
    // 100 ticks = 177in, 177.5in
  }
}
