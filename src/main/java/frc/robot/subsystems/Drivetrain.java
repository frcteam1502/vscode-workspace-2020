/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.ArrayList;
import java.util.Collections;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import frc.robot.Constants.Joysticks;
import frc.robot.commands.DriveCommand;

public class Drivetrain extends SubsystemBase {

  CANSparkMax frontLeft, backLeft, frontRight, backRight;
  Joystick left, right;

  public Drivetrain(CANSparkMax frontLeft, CANSparkMax backLeft, CANSparkMax frontRight, CANSparkMax backRight) {
    setDefaultCommand(new DriveCommand(this));
    this.frontLeft = frontLeft;
    this.backLeft = backLeft;
    this.frontRight = frontRight;
    this.backRight = backRight;
  }

  public void move() {
    double moveSpeed = Joysticks.RIGHT_JOYSTICK.getY() > .2 || Joysticks.RIGHT_JOYSTICK.getY() < -.2
        ? Joysticks.RIGHT_JOYSTICK.getY()
        : 0;
    double rotateSpeed = Joysticks.LEFT_JOYSTICK.getX() > .2 || Joysticks.LEFT_JOYSTICK.getX() < -.2
        ? Joysticks.LEFT_JOYSTICK.getX()
        : 0;
    double leftPwr = -moveSpeed + rotateSpeed;
    double rightPwr = moveSpeed + rotateSpeed;
    if ((leftPwr > 1 || leftPwr < -1) || (rightPwr > 1 || rightPwr < -1)) {
      double max = Math.abs(Math.abs(leftPwr) > Math.abs(rightPwr) ? leftPwr : rightPwr);
      leftPwr = leftPwr / max;
      rightPwr = rightPwr / max;
    }
    SmartDashboard.putNumber("Right power", rightPwr);
    SmartDashboard.putNumber("Left power", leftPwr);
    SmartDashboard.putNumber("Turn", rotateSpeed);
    SmartDashboard.putNumber("move", moveSpeed);
    SmartDashboard.putNumber("frontRight", frontRight.getEncoder().getPosition());
    SmartDashboard.putNumber("backRight", backRight.getEncoder().getPosition());
    SmartDashboard.putNumber("frontLeft", frontLeft.getEncoder().getPosition());
    SmartDashboard.putNumber("backLeft", backLeft.getEncoder().getPosition());
    frontRight.set(rightPwr);
    backRight.set(rightPwr);
    frontLeft.set(leftPwr);
    backLeft.set(-leftPwr);
  }

  @Override
  public void periodic() {
    move();
  }
}
