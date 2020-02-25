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

public class Lift extends SubsystemBase {
  private final CANSparkMax right, left;
  private final DigitalInput upper, lower;

  public Lift(CANSparkMax right, CANSparkMax left, DigitalInput upper, DigitalInput lower) {
    this.right = right;
    this.left = left;
    this.upper = upper;
    this.lower = lower;
  }

  public void setLift(double speed) {
    right.set(speed);
    left.set(speed);
  }

  public boolean getLowerLimit() {
    return lower.get();
  }

  public boolean getUpperLimit() {
    return upper.get();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
