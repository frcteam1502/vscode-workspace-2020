/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Temp extends SubsystemBase {
  /**
   * Creates a new Temp.
   */
  CANSparkMax temp;

  public Temp(CANSparkMax temp) {
    this.temp = temp;
  }

  public void run(double speed) {
    temp.set(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
