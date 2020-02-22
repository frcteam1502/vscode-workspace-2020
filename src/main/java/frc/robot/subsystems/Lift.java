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

  CANSparkMax LIFT_RIGHT;
  CANSparkMax LIFT_LEFT;
  CANSparkMax LIFT_ADJUSTMENT;
  DigitalInput LIFT_TOP_LIMIT;
  DigitalInput LIFT_BOTTOM_LIMIT;
  PIDController pid;
  ADXRS450_Gyro LIFT_GYRO;

  public Lift(CANSparkMax LIFT_RIGHT, CANSparkMax LIFT_LEFT, CANSparkMax LIFT_ADJUSTMENT, DigitalInput LIFT_TOP_LIMIT,
      DigitalInput LIFT_BOTTOM_LIMIT, ADXRS450_Gyro LIFT_GYRO) {
    pid = new PIDController(0, 0, 0);
    this.LIFT_RIGHT = LIFT_RIGHT;
    this.LIFT_LEFT = LIFT_LEFT;
    this.LIFT_ADJUSTMENT = LIFT_ADJUSTMENT;
    this.LIFT_TOP_LIMIT = LIFT_TOP_LIMIT;
    this.LIFT_BOTTOM_LIMIT = LIFT_BOTTOM_LIMIT;
    this.LIFT_GYRO = LIFT_GYRO;
  }

  public void adjust() {
    pid.input(getAngle());
    LIFT_ADJUSTMENT.set(pid.getCorrection());
  }

  public void setLift(double speed) {
    LIFT_RIGHT.set(speed);
    LIFT_LEFT.set(speed);

  }

  public void setSpeed(double speed) {
    LIFT_RIGHT.set(speed);
    LIFT_LEFT.set(speed);
    LIFT_ADJUSTMENT.set(speed);
  }

  public boolean getUpperLimit() {
    return LIFT_TOP_LIMIT.get();
  }

  public boolean getLowerLimit() {
    return LIFT_BOTTOM_LIMIT.get();
  }

  private double getAngle() {
    return LIFT_GYRO.getAngle();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
