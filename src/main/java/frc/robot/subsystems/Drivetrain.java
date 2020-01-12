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
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;

public class Drivetrain extends SubsystemBase {
  
  ArrayList<CANSparkMax> leftMotors = new ArrayList<>();
  ArrayList<CANSparkMax> rightMotors = new ArrayList<>();
  ArrayList<CANSparkMax> motors = new ArrayList<>();
  Joystick left, right;
  public Drivetrain(Joystick left, Joystick right, CANSparkMax... motors) {
    Collections.addAll(this.motors, motors); 
    leftMotors = (ArrayList<CANSparkMax>) this.motors.subList(0, (this.motors.size() - 1) / 2);
    rightMotors = (ArrayList<CANSparkMax>) this.motors.subList((this.motors.size() - 1) / 2, this.motors.size() - 1);
  }

  public void move() {
    double moveSpeed = left.getY();
    double rotateSpeed = right.getX();
    double leftPwr = -moveSpeed + rotateSpeed;
    double rightPwr = moveSpeed + rotateSpeed;
    leftMotors.forEach(x -> x.set(leftPwr));
    rightMotors.forEach(x -> x.set(rightPwr));
  }

  @Override
  public void periodic() {
    move();
  }
}
