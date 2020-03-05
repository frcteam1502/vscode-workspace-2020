/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants.Sensors;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.IntakeBelt;

public class Autonomous extends SequentialCommandGroup {
  public enum StartPosition {
    LEFT, CENTER, RIGHT
  }

  public Autonomous(Drivetrain drivetrain, IntakeBelt intakeBelt, StartPosition startPosition) {
    if (startPosition == StartPosition.LEFT) {
      addCommands(new DriveByDistance(drivetrain, -4 * 12));
      addCommands(new TurnByGyro(drivetrain, 90));
      addCommands(new DriveByDistance(drivetrain, -11 * 12));
      addCommands(new TurnByGyro(drivetrain, -90));
    } else if (startPosition == StartPosition.RIGHT) {
      addCommands(new DriveByDistance(drivetrain, -4 * 12));
      addCommands(new TurnByGyro(drivetrain, -90));
      addCommands(new DriveByDistance(drivetrain, -6 * 12));
      addCommands(new TurnByGyro(drivetrain, 90));
    }
    addCommands(new DriveToWall(drivetrain, Sensors.BACK_LIDAR));
    addCommands(new WaitCommand(0.5));
    addCommands(new RunIntakeBeltByTime(intakeBelt, 1));

  }
}
