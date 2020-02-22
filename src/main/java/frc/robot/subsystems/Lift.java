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
  private final DigitalInput UPPER_LIMIT;
  private final DigitalInput LOWER_LIMIT;
  private ADXRS450_Gyro LIFT_GYRO;

  public Lift(CANSparkMax LIFT_RIGHT, CANSparkMax LIFT_LEFT, CANSparkMax LIFT_ADJUSTMENT, DigitalInput UPPER_LIMIT,
      DigitalInput LOWER_LIMIT, ADXRS450_Gyro LIFT_GYRO) {
    this.LIFT_RIGHT = LIFT_RIGHT;
    this.LIFT_LEFT = LIFT_LEFT;
    this.LIFT_ADJUSTMENT = LIFT_ADJUSTMENT;
    this.UPPER_LIMIT = UPPER_LIMIT;
    this.LOWER_LIMIT = LOWER_LIMIT;
    this.LIFT_GYRO = LIFT_GYRO;
  }

  public void adjust(double correction) {
    LIFT_ADJUSTMENT.set(correction);
  }

  public void setLift(double speed) {
    LIFT_RIGHT.set(speed);
    LIFT_LEFT.set(speed);
  }

  public boolean getLowerLimit() {
    return LOWER_LIMIT.get();
  }

  public boolean getUpperLimit() {
    return UPPER_LIMIT.get();
  }

  public double getAngle() {
    return LIFT_GYRO.getAngle();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
