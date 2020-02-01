/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SpinnerLift extends SubsystemBase {

  CANSparkMax lift, spinner;
  DigitalInput upperLimit, lowerLimit;

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
    // This method will be called once per scheduler run
  }
}
