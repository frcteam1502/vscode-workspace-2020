/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.SolTestCommand;

public class SolenoidTest extends SubsystemBase {

  private final Solenoid solenoid;
  private boolean on = true;

  /**
   * Creates a new SolenoidTest.
   */
  public SolenoidTest(Solenoid solenoid) {
    setDefaultCommand(new SolTestCommand(this));
    this.solenoid = solenoid;
  }

  public void toggle() {
    on = !on;
  }

  public void run() {
    solenoid.set(on);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
