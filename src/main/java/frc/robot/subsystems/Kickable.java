package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.PIDController;

public class Kickable extends SubsystemBase {

  PIDController PID = new PIDController(0, 0, 0);

  private final AHRS gyro;
  private final double initial;

  CANSparkMax FRONT_LEFT = new CANSparkMax(1, MotorType.kBrushless);
  CANSparkMax BACK_LEFT = new CANSparkMax(2, MotorType.kBrushless);
  CANSparkMax FRONT_RIGHT = new CANSparkMax(3, MotorType.kBrushless);
  CANSparkMax BACK_RIGHT = new CANSparkMax(4, MotorType.kBrushless);

  public Kickable(AHRS gyro) {
    this.gyro = gyro;
    this.initial = gyro.getYaw();
  }

  public void fixRotation() {
    PID.input(gyro.getYaw() - initial);
    FRONT_RIGHT.set(PID.getCorrection());
    FRONT_LEFT.set(PID.getCorrection());
    BACK_RIGHT.set(PID.getCorrection());
    BACK_RIGHT.set(PID.getCorrection());
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
