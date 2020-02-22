package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SpinnerLift extends SubsystemBase {
  private final CANSparkMax lift;
  private final DigitalInput upperLimit, lowerLimit;

  public SpinnerLift(DigitalInput upperLimit, DigitalInput lowerLimit, CANSparkMax lift) {
    this.lift = lift;
    this.upperLimit = upperLimit;
    this.lowerLimit = lowerLimit;
  }

  public void setLift(double speed) {
    lift.set(speed);
  }

  public boolean getUpperLimit() {
    return upperLimit.get();
  }

  public boolean getLowerLimit() {
    return lowerLimit.get();
  }

  @Override
  public void periodic() {
  }
}
