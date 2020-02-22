package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {

  private final CANSparkMax wheel;

  public Intake(CANSparkMax wheel) {
    this.wheel = wheel;
  }

  public void run(double speed) {
    wheel.set(speed);
  }

  @Override
  public void periodic() {
  }
}