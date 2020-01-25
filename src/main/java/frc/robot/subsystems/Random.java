/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.RandomCommand;

public class Random extends SubsystemBase {

  private CANSparkMax motor;
  private CANEncoder enc;

  public Random(CANSparkMax motor) {
    this.motor = motor;
    setDefaultCommand(new RandomCommand(this));
  }

  public void setEncByGet() {
    enc = motor.getEncoder();
  }

  public void setEncByObj() {
    enc = new CANEncoder(motor);
  }

  public void setSpeed(double speed) {
    motor.set(speed);
    SmartDashboard.putNumber("encoder value: ", enc.getPosition());
  }

  @Override
  public void periodic() {
    setSpeed(Constants.Joysticks.LEFT_JOYSTICK.getY());
    SmartDashboard.putString("Hail marry", " worked");
    // This method will be called once per scheduler run
  }
}