/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeBelt extends SubsystemBase {
  
  private CANSparkMax top, bottom;
  
  public IntakeBelt(CANSparkMax top, CANSparkMax bottom) {
    this.top = top;
    this.bottom = bottom;
  }

  public void run() {
    top.set(1);
    bottom.set(-1);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
