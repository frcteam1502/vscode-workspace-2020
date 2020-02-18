/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
  /**
   * Creates a new Intake.
   */
  CANSparkMax RIGHT_MOTOR;
  CANSparkMax LEFT_MOTOR;
  DigitalInput ir;

  public Intake(CANSparkMax RIGHT_MOTOR, CANSparkMax LEFT_MOTOR) {
    this.LEFT_MOTOR = LEFT_MOTOR;
    this.RIGHT_MOTOR = RIGHT_MOTOR;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if(){
      ir.get();

    }
  }
}
