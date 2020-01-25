/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Timer;

public class Lidar {

    I2C sensor;

    public Lidar(I2C.Port port, int lidarAddress) {
        sensor = new I2C(port, lidarAddress);
    }

    /**
     * Simple get distance. Just expresses the ways to get various functions. write
     * was running the lidar. read was reading the specific converted distance byte.
     * can read many other byes. see documentation.
     * 
     * @return distance
     */
    public int getDistance() {
        byte[] buffer;
        buffer = new byte[2];
        sensor.write(0x00, 0x04);
        Timer.delay(0.04);
        sensor.read(0x8f, 2, buffer);
        return (int) Integer.toUnsignedLong(buffer[0]) + Byte.toUnsignedInt(buffer[1]);
    }
}