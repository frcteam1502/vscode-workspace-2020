/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Spinner extends SubsystemBase {

  CANSparkMax spinWheel;
  CANSparkMax spinLift;
  DigitalInput liftLimit;
  int colorCount = 0;
  double wheelSpeed = 1;

  public Spinner(CANSparkMax spinLift, CANSparkMax spinWheel, DigitalInput liftLimit) {
    this.spinLift = spinLift;
    this.spinWheel = spinWheel;
    this.liftLimit = liftLimit;
  }
  /*
   * public void SpinLift(double placeholder1) { spinLift.set(placeholder1); }
   * 
   * public void SpinWheel(double placeholder2) { spinWheel.set(placeholder2); }
   */

  public void run() {
    /*
     * while(!liftLimit.get()) spinLift.set(1); spinLift.set(0); //TODO Make it so
     * that it only adds 1 to colorCount every time it is on said color.
     * while(colorCount < 6) { spinWheel.set(wheelSpeed); if(checkColor()) {
     * colorCount++; wheelSpeed = wheelSpeed - .15; } } spinWheel.set(0);
     */
    CANEncoder enc = spinWheel.getEncoder();
    if (enc.getPosition() < 10000)
      spinWheel.set(1);
    else
      spinWheel.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

