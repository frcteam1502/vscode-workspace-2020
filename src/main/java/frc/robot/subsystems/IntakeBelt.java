package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.XBox;
import frc.robot.commands.MonitorBelt;

/**
 * Zoo Wee Momma. This was a good program.
 */
public class IntakeBelt extends SubsystemBase {
  private final DigitalInput infrared;
  private final CANSparkMax left, right;
  private int amountOfBalls = 0;
  private XBox controller;

  public IntakeBelt(DigitalInput infrared, CANSparkMax left, CANSparkMax right, XBox controller) {
    setDefaultCommand(new MonitorBelt(this));
    this.infrared = infrared;
    this.left = left;
    this.right = right;
    this.controller = controller;
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
    SmartDashboard.putNumber("Balls", amountOfBalls);
  }

  public boolean isBroken() {
    return !infrared.get();
    // return true;
  }

  public void setBalls(int balls) {
    amountOfBalls = balls;
  }

  public void setLeftRumble(double intensity) {
    controller.setLeftRumble(intensity);
  }

  public void incrementBalls() {
    amountOfBalls++;
  }

  public void reductBalls() {
    amountOfBalls--;
  }

  public int getBalls() {
    return amountOfBalls;
  }

  @Override
  public void periodic() {
  }
}
