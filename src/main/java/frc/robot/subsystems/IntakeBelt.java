package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.MonitorBelt;

public class IntakeBelt extends SubsystemBase {
  // private final DigitalInput infrared;
  private final CANSparkMax left, right;
  private int amountOfBalls = 0;

  public IntakeBelt(/* DigitalInput infrared, */ CANSparkMax left, CANSparkMax right) {
    setDefaultCommand(new MonitorBelt(this));
    // this.infrared = infrared;
    this.left = left;
    this.right = right;
  }

  public void init() {
    left.getEncoder().setPosition(0);
    right.getEncoder().setPosition(0);
  }

  public double getRightPosition() {
    return right.getEncoder().getPosition();
  }

  public double getLeftPosition() {
    return left.getEncoder().getPosition();
  }

  public void runBelt(double speed) {
    left.set(speed);
    right.set(-speed);
  }

  public boolean isBroken() {
    // return !infrared.get();
    return true;
  }

  public void incrementBalls() {
    amountOfBalls++;
  }

  public int getBalls() {
    return amountOfBalls;
  }

  @Override
  public void periodic() {
  }
}
