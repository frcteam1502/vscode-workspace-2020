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
  
  CANSparkMax spinLift;
  DigitalInput liftLimit;

  public SpinnerLift(CANSparkMax spinLift, DigitalInput liftLimit) {
    this.spinLift = spinLift;
    this.liftLimit = liftLimit;
  }

public void run() {
if (!liftLimit.get()) spinLift.set(1);
else spinLift.set(0);
}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
