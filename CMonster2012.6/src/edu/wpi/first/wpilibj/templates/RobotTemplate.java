/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotTemplate extends IterativeRobot {
    private BestRobotDrive drive;
    private Joystick j;
    private Servo s;
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        drive = new BestRobotDrive(1,6,4,3);
        j = new Joystick(1);
        s = new Servo(8);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        if(j.getRawButton(4))
            drive.crabLeft(.7);
        else if(j.getRawButton(5))
            drive.crabRight(.7);
        else if(j.getRawButton(3))
            drive.mecanumDrive(j);
        else
            drive.arcadeDrive(j);
        drive.setPrecisionDriving((j.getZ()+1)/2);
        
        if(j.getRawButton(8))
            s.setPosition(s.getPosition() + 0.05);
        if(j.getRawButton(9))
            s.setPosition(s.getPosition() - 0.05);
        
     //   DriverStationLCD.getInstance().println(DriverStationLCD.Line.kMain6, 1, "PD: "+Double.toString((j.getZ()+1)/2));
        SmartDashboard.putDouble("joystick scroller", (j.getZ()+1)/2);
        SmartDashboard.putDouble("Servo value", s.getPosition());
        DriverStationLCD.getInstance().updateLCD();
    }
    
}
