
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;

public class BuddyLift extends SubsystemBase {
  /**
   * Creates a new BuddyLift.
   */

  CANSparkMax motor1;
  CANSparkMax motor2;

  public BuddyLift(CANSparkMax motor1, CANSparkMax motor2) {
    this.motor1 = motor1;
    this.motor2 = motor2;
  }

  public void setSpeed(int speed) {
    motor1.set(speed);
    motor2.set(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}