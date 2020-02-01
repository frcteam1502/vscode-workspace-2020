package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Kickable extends SubsystemBase {

  private final AHRS gyro;
  private final double initial;

  public Kickable(AHRS gyro) {
    this.gyro = gyro;
    this.initial = gyro.getYaw();
  }

  public void fixRotation() {
    if (gyro.getYaw() > initial) {
      // Drivetrain.rightPwr = PIDController.getError();
    } else if (gyro.getYaw() < initial) {

    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
