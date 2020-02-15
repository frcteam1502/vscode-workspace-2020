/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.DriveByJoysticks;

public class Drivetrain extends SubsystemBase {

  final CANSparkMax frontLeft, backLeft, frontRight, backRight;

  public Drivetrain(CANSparkMax frontLeft, CANSparkMax backLeft, CANSparkMax frontRight, CANSparkMax backRight) {
    setDefaultCommand(new DriveByJoysticks(this));
    this.frontLeft = frontLeft;
    this.backLeft = backLeft;
    this.frontRight = frontRight;
    this.backRight = backRight;
  }

  public void move(double leftPower, double rightPower) {
    frontLeft.set(leftPower);
    backLeft.set(leftPower);
    frontRight.set(rightPower);
    backRight.set(rightPower);
  }

  public double getLeftEncoderPosition() {
    return (frontLeft.getEncoder().getPosition() + backLeft.getEncoder().getPosition()) / 2;
  }

  public double getRightEncoderPosition() {
    return -(frontRight.getEncoder().getPosition() + backRight.getEncoder().getPosition()) / 2;
  }

  public double getLeftEncoderVelocity() {
    return (frontLeft.getEncoder().getVelocity() + backLeft.getEncoder().getVelocity()) / 2;
  }

  public double getRightEncoderVelocity() {
    return -(frontRight.getEncoder().getVelocity() + backRight.getEncoder().getVelocity()) / 2;
  }
}