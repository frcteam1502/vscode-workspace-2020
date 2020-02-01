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

  public void move() {
    double moveSpeed = Joysticks.LEFT_JOYSTICK.getY();
    double rotateSpeed = Joysticks.RIGHT_JOYSTICK.getX();
    double leftPwr = Math.pow(-moveSpeed + rotateSpeed, 3);
    double rightPwr = Math.pow(moveSpeed + rotateSpeed, 3);
    if (moveSpeed >= 1 || rotateSpeed >= 1) {
      double max = moveSpeed >= rotateSpeed ? moveSpeed : rotateSpeed;
      leftPwr /= max;
      rightPwr /= max;
    }
    LEFT_FRONT.set(leftPwr);
    LEFT_BACK.set(leftPwr);
    RIGHT_FRONT.set(rightPwr);
    RIGHT_BACK.set(rightPwr);
  }

  @Override
  public void periodic() {
  }
}
