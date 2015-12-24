/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.robotsbythec;


import edu.wpi.first.wpilibj.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotMain extends IterativeRobot
{
    ADXL345_I2C accel;
    double time=0;
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit()
    {
        accel = new ADXL345_I2C(1, ADXL345_I2C.DataFormat_Range.k4G);
        DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser3, 1, "robot initalized");
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic()
    {

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic()
    {
        DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser3, 1, "outputting accel data");
        //System.out.println("i'm alive");
        //System.out.println(accel.getAcceleration(ADXL345_I2C.Axes.kX));
        if(Timer.getFPGATimestamp()-time>.01){
            SmartDashboard.log(accel.getAcceleration(ADXL345_I2C.Axes.kX)*100, "accel x axis");
            SmartDashboard.log(accel.getAcceleration(ADXL345_I2C.Axes.kY)*100, "accel y axis");
            SmartDashboard.log(accel.getAcceleration(ADXL345_I2C.Axes.kZ)*100, "accel z axis");
            time=Timer.getFPGATimestamp();
        }
        
    }
    
}
