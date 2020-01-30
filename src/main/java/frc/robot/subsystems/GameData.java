/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class GameData extends SubsystemBase {
  /**
   * Creates a new GameData.
   */

  enum Colors {
    Red('R'), Green('G'), Blue('B'), Yellow('Y');

    char initial;

    Colors(char initial) {
      this.initial = initial;
    }

    @Override
    public String toString() {
      return Character.toString(initial);
    }
  }

  public GameData() {

  }

  public void GetData() {

  }

  public static Colors GetColor(char c) {
    for (Colors color : Colors.values()) {
      if (color.initial == c) {
        return color;
      }
    }
    return null;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
