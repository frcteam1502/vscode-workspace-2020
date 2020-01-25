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
import frc.robot.Constants;
import frc.robot.PIDController;

public class Lift extends SubsystemBase {

  CANSparkMax LIFT_RIGHT;
  CANSparkMax LIFT_LEFT;
  CANSparkMax LIFT_ADJUSTMENT;
  DigitalInput LIFT_TOP_LIMITS;
  DigitalInput LIFT_BOTTOM_LIMITS;
  PIDController pid;

  public Lift(CANSparkMax LIFT_RIGHT, CANSparkMax LIFT_LEFT, NavX LIFT_GYRO, CANSparkMax LIFT_ADJUSTMENT,
      DigitalInput LIFT_TOP_LIMITS, DigitalInput LIFT_BOTTOM_LIMITS) {
    pid = new PIDController(0, 0, 0);
    this.LIFT_RIGHT = LIFT_RIGHT;
    this.LIFT_LEFT = LIFT_LEFT;
    this.LIFT_ADJUSTMENT = LIFT_ADJUSTMENT;
    this.LIFT_TOP_LIMITS = LIFT_TOP_LIMITS;
    this.LIFT_BOTTOM_LIMITS = LIFT_BOTTOM_LIMITS;
  }

  public void adjust() {
    pid.input(Constants.Sensors.LIFT_GYRO.getRoll());
    LIFT_ADJUSTMENT.set(pid.getCorrection());
  }

  public void setSpeed(double speed) {
    LIFT_RIGHT.set(speed);
    LIFT_LEFT.set(speed);
    LIFT_ADJUSTMENT.set(speed);
  }

  public void runUp() {
    if (!LIFT_TOP_LIMITS.get()) {
      LIFT_RIGHT.set(1);
      LIFT_LEFT.set(1);
    } else {
      runDown();
    }
  }

  public void runDown() {
    if (!LIFT_BOTTOM_LIMITS.get()) {
      LIFT_LEFT.set(-1);
      LIFT_RIGHT.set(-1);
    } else {
      LIFT_LEFT.set(0);
      LIFT_RIGHT.set(0);
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
