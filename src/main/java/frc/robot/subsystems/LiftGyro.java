/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.PIDController;
import frc.robot.commands.LiftCommand;

public class LiftGyro extends SubsystemBase {
  private CANSparkMax motor;
  private ADXRS450_Gyro gyro;

  private PIDController pid = new PIDController(1e-3, 0, 0);

  /**
   * Creates a new LiftGyro.
   */
  public LiftGyro(CANSparkMax motor, ADXRS450_Gyro gyro) {
    setDefaultCommand(new LiftCommand(this));
    this.motor = motor;
    this.gyro = gyro;
  }

  public void runMotor() {
    SmartDashboard.putNumber("Angle", gyro.getAngle());
    double number = pid.getCorrection(gyro.getAngle());
    SmartDashboard.putNumber("Correction", number);
    motor.set(number);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
