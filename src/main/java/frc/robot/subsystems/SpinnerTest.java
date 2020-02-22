/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SpinnerTest extends SubsystemBase {
  /**
   * Creates a new SpinnerTest.
   */
  CANSparkMax wheel;

  public SpinnerTest(CANSparkMax wheel) {
    this.wheel = wheel;
    this.wheel.getEncoder().setPosition(0);
  }

  public void init() {
    this.wheel.getEncoder().setPosition(0);
  }

  public void setSpeed(double speed) {
    wheel.set(speed);
  }

  public boolean isDone() {
    SmartDashboard.putNumber("Distance per encoder)", Math.acos(-188 / -512) * 16);
    SmartDashboard.putNumber("Encoder", wheel.getEncoder().getPosition());
    return wheel.getEncoder().getPosition() > 1;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
