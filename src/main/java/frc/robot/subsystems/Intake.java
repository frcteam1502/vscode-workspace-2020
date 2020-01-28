/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {

  private CANSparkMax top, bottom, intakeWheel;
  private boolean beltOn = true;

  public Intake(CANSparkMax top, CANSparkMax bottom, CANSparkMax intakeWheel) {
    this.top = top;
    this.bottom = bottom;
    this.intakeWheel = intakeWheel;
  }

  public void run() {
    int beltSpeed = beltOn ? 1 : 0;
    top.set(beltSpeed);
    bottom.set(-beltSpeed);
    intakeWheel.set(1);
  }

  public void stop() {
    top.set(0);
    bottom.set(0);
    intakeWheel.set(0);
    beltOn = false;
  }

  public void toggleBelt() {
    beltOn = !beltOn;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}