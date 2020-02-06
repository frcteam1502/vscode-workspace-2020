/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Lidar;
import frc.robot.PIDController;
import static frc.robot.Constants.Joysticks.*;

public class LidarDriveTrain extends SubsystemBase {
  /**
   * Creates a new LidarDriveTrain.
   */
  private final CANSparkMax FRONT_LEFT;
  private final CANSparkMax BACK_LEFT;
  private final CANSparkMax FRONT_RIGHT;
  private final CANSparkMax BACK_RIGHT;
  private final Lidar LIDAR;
  private static final double UNITCONVERTION = 6 * Math.PI / 10.9 * 2.54;
  private static final double TIME = 0.5;
  private final PIDController PID;

  public LidarDriveTrain(CANSparkMax FRONT_LEFT, CANSparkMax BACK_LEFT, CANSparkMax FRONT_RIGHT, CANSparkMax BACK_RIGHT,
      Lidar LIDAR, PIDController PID) {
    this.BACK_LEFT = BACK_LEFT;
    this.BACK_RIGHT = BACK_RIGHT;
    this.FRONT_LEFT = FRONT_LEFT;
    this.FRONT_RIGHT = FRONT_RIGHT;
    this.LIDAR = LIDAR;
    this.PID = PID;
  }

  public void run() {
    double frontRightVelocity = FRONT_RIGHT.getEncoder().getVelocity() * UNITCONVERTION;
    double frontLeftVelocity = FRONT_LEFT.getEncoder().getVelocity() * UNITCONVERTION;
    double lidarDistance = LIDAR.getDistance();

    if (frontLeftVelocity * TIME >= lidarDistance || frontRightVelocity * TIME >= lidarDistance) {
      double moveSpeed = LEFT_JOYSTICK.getY() > .1 ? Math.pow(LEFT_JOYSTICK.getY(), 3) : 0;
      double rotateSpeed = RIGHT_JOYSTICK.getX() > .1 ? Math.pow(RIGHT_JOYSTICK.getX(), 3) : 0;
      double leftPwr = -moveSpeed + rotateSpeed;
      double rightPwr = moveSpeed + rotateSpeed;

      if ((leftPwr > 1 || leftPwr < -1) || (rightPwr > 1 || rightPwr < -1)) {
        double max = Math.abs(Math.abs(leftPwr) > Math.abs(rightPwr) ? leftPwr : rightPwr);
        leftPwr = leftPwr / max;
        rightPwr = rightPwr / max;
      }

      PID.input(lidarDistance);
      leftPwr *= PID.getCorrection();
      rightPwr *= PID.getCorrection();

      FRONT_LEFT.set(leftPwr);
      BACK_LEFT.set(leftPwr);
      FRONT_RIGHT.set(rightPwr);
      BACK_RIGHT.set(rightPwr);
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
