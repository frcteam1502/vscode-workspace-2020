/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.fasterxml.jackson.databind.deser.std.StackTraceElementDeserializer;
import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.Joysticks.*;

import java.util.ArrayList;
import java.util.Arrays;

public class SkidDriveTrain extends SubsystemBase {
  private final AHRS navx;

  private static final double UNITCONVERT = 6 * Math.PI / 10.9 * 2.54;
  private final double targetHigh = 100;
  private final double targetLow = 50;
  private double target = targetHigh;
  ArrayList<CANSparkMax> motors = new ArrayList<>();
  ArrayList<CANSparkMax> leftMotors = new ArrayList<>();
  ArrayList<CANSparkMax> rightMotors = new ArrayList<>();

  public SkidDriveTrain(AHRS navx, CANSparkMax... motors) {
    this.motors = (ArrayList<CANSparkMax>) Arrays.asList(motors);
    this.navx = navx;
  }

  public void move() {
    double moveSpeed = LEFT_JOYSTICK.getY() > .1 ? Math.pow(LEFT_JOYSTICK.getY(), 3) : 0;
    double rotateSpeed = RIGHT_JOYSTICK.getX() > .1 ? Math.pow(RIGHT_JOYSTICK.getX(), 3) : 0;
    double leftPwr = -moveSpeed + rotateSpeed;
    double rightPwr = moveSpeed + rotateSpeed;
    if ((leftPwr > 1 || leftPwr < -1) || (rightPwr > 1 || rightPwr < -1)) {
      double max = Math.abs(Math.abs(leftPwr) > Math.abs(rightPwr) ? leftPwr : rightPwr);
      leftPwr = leftPwr / max;
      rightPwr = rightPwr / max;
    }
    setMotors(leftMotors, leftPwr);
    setMotors(rightMotors, rightPwr);

    if (isSkidding()) {
      skidControl();
    }
  }

  private void initMotors() {
    for (CANSparkMax x : motors) {
      x.setOpenLoopRampRate(2);
      x.getEncoder().setPosition(0);
    }
    rightMotors = (ArrayList<CANSparkMax>) motors.subList(0, motors.size() / 2);
    leftMotors = (ArrayList<CANSparkMax>) motors.subList(motors.size() / 2 + 1, motors.size());
  }

  private void setMotors(ArrayList<CANSparkMax> motors, final double speed) {
    motors.forEach(x -> x.set(speed));
  }

  private double getPosition(ArrayList<CANSparkMax> motors) {
    double val = 0;
    for (CANSparkMax x : motors)
      val += x.getEncoder().getPosition();
    return val / motors.size();
  }

  private void toggleTarget() {
    target = target == targetHigh ? targetLow : targetHigh;
  }

  /*
   * get position target position if position != target move
   */
  public void skidControl() {
    double avgPosition = (getPosition(leftMotors) + getPosition(rightMotors)) / 2;

    if (avgPosition == target)
      toggleTarget();
    else if (avgPosition > target) {
      setMotors(leftMotors, .2);
      setMotors(rightMotors, .2);
    } else if (avgPosition < target)
      setMotors(leftMotors, -.2);
    setMotors(rightMotors, -.2);
  }

  public boolean isSkidding() {
    double moveSpeed = LEFT_JOYSTICK.getY();
    double forwardVelocity = Math.sqrt(Math.pow(navx.getVelocityX(), 2) + Math.pow(navx.getVelocityZ(), 2))
        * Math.abs(moveSpeed) / moveSpeed;
    // TODO: Fix this plez soon
    return motors.get(0).getEncoder().getVelocity() * UNITCONVERT <= forwardVelocity + 20
        || motors.get(0).getEncoder().getVelocity() * UNITCONVERT >= forwardVelocity - 20;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    move();
  }
}
