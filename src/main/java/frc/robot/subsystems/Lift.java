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
  // private final DigitalInput upper, lower;
  private final double encoderCycleDistance = .5;

  public Lift(CANSparkMax right, CANSparkMax left/* , DigitalInput upper, DigitalInput lower */) {
    this.right = right;
    this.left = left;
    // this.upper = upper;
    // this.lower = lower;
  }

  public void setLift(double speed) {
    right.set(-speed);
    left.set(speed);
  }

  public void setRight(double speed) {
    right.set(speed);
  }

  public double getRightVelocity() {
    return right.getEncoder().getVelocity();
  }

  public double getLeftVelocity() {
    return left.getEncoder().getVelocity();
  }

  public void setLeft(double speed) {
    left.set(speed);
  }

  public void setEncoders(double place) {
    left.getEncoder().setPosition(place);
    right.getEncoder().setPosition(place);
  }

  public double getLeftEncoder() {
    return left.getEncoder().getPosition();
  }

  public double getRightEncoder() {
    return right.getEncoder().getPosition();
  }

  public boolean getLowerLimit() {
    // return lower.get();
    return false;
  }

  public boolean getUpperLimit() {
    // return upper.get();
    return false;
  }

  public double getCycleDistance() {
    return encoderCycleDistance;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
