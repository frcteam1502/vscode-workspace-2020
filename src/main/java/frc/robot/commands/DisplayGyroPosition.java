/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.ADXL345_I2C;
import edu.wpi.first.wpilibj.ADXL345_I2C.Axes;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;

public class DisplayGyroPosition extends CommandBase {

  public DisplayGyroPosition() {
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    ADXL345_I2C gyro = Constants.Sensors.GYRO;
    SmartDashboard.putNumber("Gyro X", gyro.getX());
    SmartDashboard.putNumber("Gyro Y", gyro.getY());
    SmartDashboard.putNumber("Gyro Z", gyro.getZ());
    SmartDashboard.putNumber("X Axis Acceleration", gyro.getAcceleration(Axes.kX));
    SmartDashboard.putNumber("Y Axis Acceleration", gyro.getAcceleration(Axes.kY));
    SmartDashboard.putNumber("Z Axis Acceleration", gyro.getAcceleration(Axes.kZ));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
