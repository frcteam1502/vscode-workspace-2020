/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.ReverseCommandDriveTrain;

import static frc.robot.Constants.Joysticks.*;

public class ReverseControls extends SubsystemBase {
  /**
   * Creates a new ReverseControls.
   */
  CANSparkMax FRONT_LEFT;
  CANSparkMax FRONT_RIGHT;
  CANSparkMax BACK_LEFT;
  CANSparkMax BACK_RIGHT;
  int direction = 1;

  public ReverseControls(CANSparkMax FRONT_LEFT, CANSparkMax FRONT_RIGHT, CANSparkMax BACK_LEFT,
      CANSparkMax BACK_RIGHT) {

    setDefaultCommand(new ReverseCommandDriveTrain(this));
    this.BACK_LEFT = BACK_LEFT;
    this.BACK_RIGHT = BACK_RIGHT;
    this.FRONT_LEFT = FRONT_LEFT;
    this.FRONT_RIGHT = FRONT_RIGHT;

  }

  public void move() {
    double moveSpeed = LEFT_JOYSTICK.getY() > .1 ? Math.pow(LEFT_JOYSTICK.getY(), 3) : 0;
    double rotateSpeed = RIGHT_JOYSTICK.getX() > .1 ? Math.pow(RIGHT_JOYSTICK.getX(), 3) : 0;
    double leftPwr = -direction * moveSpeed + rotateSpeed;
    double rightPwr = direction * moveSpeed + rotateSpeed;
    if ((leftPwr > 1 || leftPwr < -1) || (rightPwr > 1 || rightPwr < -1)) {
      double max = Math.abs(Math.abs(leftPwr) > Math.abs(rightPwr) ? leftPwr : rightPwr);
      leftPwr = leftPwr / max;
      rightPwr = rightPwr / max;
    }
    FRONT_LEFT.set(leftPwr);
    BACK_LEFT.set(leftPwr);
    FRONT_RIGHT.set(rightPwr);
    BACK_RIGHT.set(rightPwr);
  }

  public void reverse() {
    direction *= -1;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
