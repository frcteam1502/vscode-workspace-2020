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

public class SpinnerLiftUp extends SubsystemBase {

  CANSparkMax spinLift;
  DigitalInput liftLimit;
  DigitalInput lowerLimit;

  public SpinnerLiftUp(CANSparkMax spinLift, DigitalInput liftLimit, DigitalInput lowerLimit) {
    this.spinLift = spinLift;
    this.liftLimit = liftLimit;
    this.lowerLimit = lowerLimit;
  }

  public void runUp() {
    if (!liftLimit.get())
      spinLift.set(1);
    else
      spinLift.set(0);
  }

  public void runDown() {
    if (!lowerLimit.get())
      spinLift.set(-1);
    else
      spinLift.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
