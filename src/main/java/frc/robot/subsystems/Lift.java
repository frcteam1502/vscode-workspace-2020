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

public class Lift extends SubsystemBase {

  CANSparkMax LIFT_RIGHT;
  CANSparkMax LIFT_LEFT;
  CANSparkMax LIFT_ADJUSTMENT;
  DigitalInput LIFT_LIMITS;

      
  public Lift(CANSparkMax LIFT_RIGHT, CANSparkMax LIFT_LEFT, NavX LIFT_GYRO, CANSparkMax LIFT_ADJUSTMENT, DigitalInput LIFT_LIMITS) {
    this.LIFT_RIGHT = LIFT_RIGHT;
    this.LIFT_LEFT = LIFT_LEFT;
    this.LIFT_ADJUSTMENT = LIFT_ADJUSTMENT;
    this.LIFT_LIMITS = LIFT_LIMITS;
  }

  public void setSpeed(double speed) {
    LIFT_RIGHT.set(speed);
    LIFT_LEFT.set(speed);
    LIFT_ADJUSTMENT.set(speed);
  }

  // public void run(){ 
  // }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
