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
import frc.robot.commands.RunBelt;

public class IntakeBelt extends SubsystemBase {

  private DigitalInput infrared;
  private CANSparkMax left, right;

  /**
   * Creates a new Intake.
   */
  public IntakeBelt(DigitalInput infrared, CANSparkMax left, CANSparkMax right) {
    setDefaultCommand(new RunBelt(this));
    this.infrared = infrared;
    this.left = left;
    this.right = right;
  }

  public boolean isBroken() {
    return !infrared.get();
  }

  public void runBelt(int speed) {
    left.set(speed);
    right.set(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}