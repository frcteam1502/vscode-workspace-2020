package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
  private final VictorSPX wheel;

  public Intake(VictorSPX wheel) {
    this.wheel = wheel;
    wheel.configOpenloopRamp(.5);
  }

  public void run(double speed) {
    wheel.set(ControlMode.PercentOutput, speed);
  }

  @Override
  public void periodic() {
  }
}