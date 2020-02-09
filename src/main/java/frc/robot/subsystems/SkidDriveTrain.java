/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.Joysticks.*;

import java.util.Arrays;
import java.util.function.Function;

public class SkidDriveTrain extends SubsystemBase {
  private final AHRS navx;

  private static final double UNIT_CONVERT = 6 * Math.PI / 10.9 * 2.54;
  //TODO needs to relate to current position + amount of movemenet
  private final double targetHigh = 100;
  private final double targetLow = 50;
  private double target = targetHigh;
  private final CANSparkMax[] motors, leftMotors, rightMotors;

  public SkidDriveTrain(AHRS navx, CANSparkMax FRONT_LEFT, CANSparkMax BACK_LEFT, CANSparkMax FRONT_RIGHT, CANSparkMax BACK_RIGHT) {
    this.motors = new CANSparkMax[] {FRONT_LEFT, BACK_LEFT, FRONT_RIGHT, BACK_RIGHT};
    this.navx = navx;
    this.leftMotors = new CANSparkMax[] {FRONT_LEFT, BACK_LEFT};
    this.rightMotors = new CANSparkMax[] {FRONT_RIGHT, BACK_RIGHT};
  }

  private void setMotors(final double speed, CANSparkMax... motors) {
    Arrays.asList(motors).forEach(x -> x.set(speed));
  }

  private double average(Function<CANSparkMax, Double> func, CANSparkMax... motors) {
    double val = 0;
    for (CANSparkMax x : motors)
      val += func.apply(x);
    return val / motors.length;
  }

  private boolean isSkidding(double moveSpeed) {
    // TODO fix this you morons
    double actualVelocity = Math.sqrt(Math.pow(navx.getVelocityX(), 2) + Math.pow(navx.getVelocityZ(), 2)) * Math.abs(moveSpeed) / moveSpeed;
    double expectedVelocity = average(x -> x.getEncoder().getVelocity(), motors) * UNIT_CONVERT;
    boolean wheelsSkidding = expectedVelocity <= actualVelocity + 20 || expectedVelocity <= actualVelocity - 20;
    boolean botSkidding = expectedVelocity >= actualVelocity - 20 || expectedVelocity >= actualVelocity + 20;
    return wheelsSkidding || botSkidding;
  }

  /*
   * get position target position if position != target move
   */
  private double skidControl(double moveSpeed) {
    if (isSkidding(moveSpeed)) {
      double avgPosition = (average(x -> x.getEncoder().getPosition(), motors));
      if (avgPosition == target) target = target == targetHigh ? targetLow : targetHigh;
      return avgPosition > target ? .2 : -.2;
    }
    else return moveSpeed;
  }

  public void initMotors() {
    for (CANSparkMax x : motors) {
      x.setOpenLoopRampRate(2);
      x.getEncoder().setPosition(0);
    }
  }

  public void move() {
    double moveSpeed = LEFT_JOYSTICK.getY() > .1 ? Math.pow(LEFT_JOYSTICK.getY(), 3) : 0;
    double rotateSpeed = RIGHT_JOYSTICK.getX() > .1 ? Math.pow(RIGHT_JOYSTICK.getX(), 3) : 0;
    moveSpeed = skidControl(moveSpeed);
    double leftPwr = -moveSpeed + rotateSpeed;
    double rightPwr = moveSpeed + rotateSpeed;
    if ((leftPwr > 1 || leftPwr < -1) || (rightPwr > 1 || rightPwr < -1)) {
      double max = Math.abs(Math.abs(leftPwr) > Math.abs(rightPwr) ? leftPwr : rightPwr);
      leftPwr = leftPwr / max;
      rightPwr = rightPwr / max;
    }
    setMotors(leftPwr, leftMotors);
    setMotors(rightPwr, rightMotors);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    move();
  }
}
