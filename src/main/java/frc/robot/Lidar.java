/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;


public class Lidar {

  I2C sensor;

  int count;

  public Lidar(I2C sensor) {
    this.sensor = sensor;
    count = 0;
  }

  /**
   * Simple get distance.
   * Just expresses the ways to get various functions.
   * write was running the lidar.
   * read was reading the specific converted distance byte.
   * can read many other byes.
   * see documentation.
   * @return distance
   */
  public int getDistance() {
    if (count == 100) {
      count = 0; //Restart the count every 100
    }

    byte [] buffer;
    byte [] busy;
    buffer = new byte[2];
    busy = new byte[1];

    if (count == 0) {    //Write w/ receiver bias every 100 measurements
      sensor.write(0x00, 0x04);
    } else {    //Write w/o receiver bias
      sensor.write(0x00, 0x03);
    }
    count++;

    sensor.read(0x01, 1, busy);
    while ((Byte.toUnsignedInt(busy[0]) & 0x00) != 0x00) {
      Timer.delay(.001);
    }
    sensor.read(0x8f, 2, buffer);
    
    return (int)Integer.toUnsignedLong(buffer[0]) + Byte.toUnsignedInt(buffer[1]);	//swap this if it doesn't look right
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
