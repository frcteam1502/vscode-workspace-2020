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
  CANSparkMax temp2;

  public Temp(CANSparkMax temp, CANSparkMax temp2) {
    this.temp = temp;
    this.temp2 = temp2;
  }

  public void init() {
    temp.getEncoder().setPosition(0);
    temp2.getEncoder().setPosition(0);
  }

  public void run(double speed) {
    temp.set(speed);
    temp2.set(-speed);
  }

  public boolean isDone(double distance) {
    return !(temp.getEncoder().getPosition() <= distance && temp2.getEncoder().getPosition() <= distance);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
