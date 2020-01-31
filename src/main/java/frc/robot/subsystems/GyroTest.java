/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.PIDController;

public class GyroTest extends SubsystemBase {
  /**
   * Creates a new GyroTest.
   */
  final ADXRS450_Gyro TEST_GYRO;
  PIDController pid;

  public GyroTest(ADXRS450_Gyro TEST_GYRO) {
    this.TEST_GYRO = TEST_GYRO;
    pid = new PIDController(0, 0, 0);

  }

  public void run(){
    SmartDashboard.putNumber("Gyro angle", TEST_GYRO.getAngle());
    pid.input(TEST_GYRO.getAngle()));
    TEST_GYRO.getAngle(); 
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
