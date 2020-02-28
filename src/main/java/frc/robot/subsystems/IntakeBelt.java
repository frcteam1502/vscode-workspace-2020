package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.RunBelt;

public class IntakeBelt extends SubsystemBase {
  private final DigitalInput infrared;
  private final CANSparkMax left, right;
  private int amountOfBalls = 0;
  private int encodersPerBall = 0;
  private boolean isBroken = false;

  public IntakeBelt(DigitalInput infrared, CANSparkMax left, CANSparkMax right) {
    setDefaultCommand(new RunBelt(this));
    this.infrared = infrared;
    this.left = left;
    this.right = right;
  }

  public boolean isBroken() {
    return !infrared.get();
  }

  public void runBelt(int speed) {
    left.set(speed);
    right.set(speed);
  }

  public void adjustToBallIndex() {
    double speed = 0;
    if (isBroken == infrared.get() && !infrared.get())
      amountOfBalls++;
    if (left.getEncoder().getPosition() < amountOfBalls * encodersPerBall
        && right.getEncoder().getPosition() > amountOfBalls * encodersPerBall)
      speed = 1;
    else if (left.getEncoder().getPosition() > amountOfBalls * encodersPerBall
        && right.getEncoder().getPosition() < amountOfBalls * encodersPerBall)
      speed = -1;
    left.set(speed);
    right.set(-speed);
  }

  @Override
  public void periodic() {
  }
}
