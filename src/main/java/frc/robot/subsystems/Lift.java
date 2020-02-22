/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.PIDController;

public class Lift extends SubsystemBase {

  private final CANSparkMax LIFT_RIGHT;
  private final CANSparkMax LIFT_LEFT;
  private final CANSparkMax LIFT_ADJUSTMENT;
  private final DigitalInput LIFT_TOP_LIMITS;
  private final DigitalInput LIFT_BOTTOM_LIMITS;
  PIDController pid;
  ADXRS450_Gyro LIFT_GYRO;

  public Lift(CANSparkMax LIFT_RIGHT, CANSparkMax LIFT_LEFT, CANSparkMax LIFT_ADJUSTMENT, DigitalInput LIFT_TOP_LIMITS,
      DigitalInput LIFT_BOTTOM_LIMITS, ADXRS450_Gyro LIFT_GYRO) {
    pid = new PIDController(0, 0, 0);
    this.LIFT_RIGHT = LIFT_RIGHT;
    this.LIFT_LEFT = LIFT_LEFT;
    this.LIFT_ADJUSTMENT = LIFT_ADJUSTMENT;
    this.LIFT_TOP_LIMITS = LIFT_TOP_LIMITS;
    this.LIFT_BOTTOM_LIMITS = LIFT_BOTTOM_LIMITS;
    this.LIFT_GYRO = LIFT_GYRO;
  }

  public void adjust() {
    LIFT_ADJUSTMENT.set(pid.getCorrection(getAngle()));
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

  private double getAngle() {
    return LIFT_GYRO.getAngle();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
