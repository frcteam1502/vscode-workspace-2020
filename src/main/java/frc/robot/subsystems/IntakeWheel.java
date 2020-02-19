/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeWheel extends SubsystemBase {
  /**
   * Creates a new IntakeWheel.
   */

  private CANSparkMax wheel;

  public IntakeWheel(CANSparkMax wheel) {
    this.wheel = wheel;
  }

  public void in() {
    wheel.set(1);
  }

  public void out() {
    wheel.set(-1);
  }

  public void off() {
    wheel.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    wheel.set(1);
  }
}
