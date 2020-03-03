package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.XBox;

public class Intake extends SubsystemBase {
  private final VictorSPX wheel;
  private final XBox controller;

  public Intake(VictorSPX wheel, XBox controller) {
    this.wheel = wheel;
    this.controller = controller;
    wheel.configOpenloopRamp(.5);
  }

  public void setRightRumble(double intensity) {
    controller.setRightRumble(intensity);
  }

  public void run(double speed) {
    wheel.set(ControlMode.PercentOutput, speed);
  }

  @Override
  public void periodic() {
  }
}