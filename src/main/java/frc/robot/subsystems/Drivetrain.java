/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Joysticks;
import frc.robot.commands.DriveCommand;

public class Drivetrain extends SubsystemBase {

  CANSparkMax LEFT_FRONT, LEFT_BACK, RIGHT_FRONT, RIGHT_BACK;

  public Drivetrain(CANSparkMax LEFT_FRONT, CANSparkMax LEFT_BACK, CANSparkMax RIGHT_FRONT, CANSparkMax RIGHT_BACK) {
    setDefaultCommand(new DriveCommand(this));
    this.LEFT_FRONT = LEFT_FRONT;
    this.LEFT_BACK = LEFT_BACK;
    this.RIGHT_FRONT = RIGHT_FRONT;
    this.RIGHT_BACK = RIGHT_BACK;
  }

  public void moveByJoysticks() {
    double moveSpeed = Joysticks.LEFT_JOYSTICK.getY();
    double rotateSpeed = Joysticks.RIGHT_JOYSTICK.getX();
    double leftPwr = Math.pow(moveSpeed - rotateSpeed, 3);
    double rightPwr = Math.pow(moveSpeed + rotateSpeed, 3);
    if (moveSpeed >= 1 || rotateSpeed >= 1) {
      double max = moveSpeed >= rotateSpeed ? moveSpeed : rotateSpeed;
      leftPwr /= max;
      rightPwr /= max;
    }
    move(leftPwr, rightPwr);
  }

  public void move(double leftPower, double rightPower) {
    LEFT_FRONT.set(-leftPower);
    LEFT_BACK.set(-leftPower);
    RIGHT_FRONT.set(rightPower);
    RIGHT_BACK.set(rightPower);
  }

  public void resetEncoders() {
    LEFT_FRONT.getEncoder().setPosition(0);
    LEFT_BACK.getEncoder().setPosition(0);
    RIGHT_FRONT.getEncoder().setPosition(0);
    RIGHT_BACK.getEncoder().setPosition(0);
  }

  public double getLeftEncoderPosition() {
    return -(LEFT_FRONT.getEncoder().getPosition() + LEFT_BACK.getEncoder().getPosition()) / 2;
  }

  public double getRightEncoderPosition() {
    return (RIGHT_FRONT.getEncoder().getPosition() + RIGHT_BACK.getEncoder().getPosition()) / 2;
  }

  @Override
  public void periodic() {
  }
}
