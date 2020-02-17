/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Buttons;
import frc.robot.Constants.Joysticks;
import frc.robot.Constants.Sensors;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.TempDrive;

public class Drivetrain extends SubsystemBase {

  public CANSparkMax frontLeft, backLeft, frontRight, backRight;
  Joystick left, right;
  LidarSubsystem lidar;

  public Drivetrain(CANSparkMax frontLeft, CANSparkMax backLeft, CANSparkMax frontRight, CANSparkMax backRight) {
    setDefaultCommand(new TempDrive(this));
    this.frontLeft = frontLeft;
    this.backLeft = backLeft;
    this.frontRight = frontRight;
    this.backRight = backRight;
    this.lidar = new LidarSubsystem(Sensors.BACK_LIDAR);
  }

  public double getEncoder() {
    return frontLeft.getEncoder().getPosition();
  }

  public void move() {
    double moveSpeed = Joysticks.RIGHT_JOYSTICK.getY() > .1 || Joysticks.RIGHT_JOYSTICK.getY() < -.1
        ? Joysticks.RIGHT_JOYSTICK.getY()
        : 0;
    double rotateSpeed = Joysticks.LEFT_JOYSTICK.getX() > .1 || Joysticks.LEFT_JOYSTICK.getX() < -.1
        ? Joysticks.LEFT_JOYSTICK.getX()
        : 0;
    moveSpeed = Math.pow(moveSpeed, 3);
    rotateSpeed = Math.pow(rotateSpeed, 3);
    double leftPwr = -moveSpeed + rotateSpeed;
    double rightPwr = moveSpeed + rotateSpeed;
    if ((leftPwr > 1 || leftPwr < -1) || (rightPwr > 1 || rightPwr < -1)) {
      double max = Math.abs(Math.abs(leftPwr) > Math.abs(rightPwr) ? leftPwr : rightPwr);
      leftPwr = leftPwr / max;
      rightPwr = rightPwr / max;
    }
    if (lidar.getCM() < 400 && Buttons.RIGHT_TRIGGER.get()) {
      leftPwr = rightPwr = 0;
    }
    SmartDashboard.putNumber("Right power", rightPwr);
    SmartDashboard.putNumber("Left power", leftPwr);
    frontRight.set(rightPwr);
    backRight.set(rightPwr);
    frontLeft.set(leftPwr);
    backLeft.set(leftPwr);
  }

  @Override
  public void periodic() {
  }
}
