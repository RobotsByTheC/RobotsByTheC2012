/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.robotsbythec;


//import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotMain extends IterativeRobot
{
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    
    private Emotions emotions;
    private double oldTime1 = 0;
    private double oldTime2 = 0;
    private double matchTime = 0;
    private boolean thirtySecondWarningToggle = false;
    
    public void robotInit()
    {
        emotions = new Emotions();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic()
    {
        emotions.display(emotions.seriousFace);
    }
    
    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic()
    {
        emotions.display(emotions.happyFace);
        
        SmartDashboard.putDouble("Match time", (135.0 - DriverStation.getInstance().getMatchTime()));
        SmartDashboard.putDouble("FPGA time", Timer.getFPGATimestamp());
        SmartDashboard.putDouble("old time", oldTime1);
        
        if(135.0 - DriverStation.getInstance().getMatchTime() <= 30)
        {
            if(Timer.getFPGATimestamp() - oldTime1 >= 0.1)
            {
                if(thirtySecondWarningToggle)
                    SmartDashboard.putBoolean("30 SEC LEFT!!", true);
                else
                    SmartDashboard.putBoolean("30 SEC LEFT!!", false);

                thirtySecondWarningToggle = !thirtySecondWarningToggle;
                
                oldTime1 = Timer.getFPGATimestamp();
            }
        }
    }
    
    public void disabledInit()
    {
        oldTime1 = Timer.getFPGATimestamp();
    }
        
    public void disabledPeriodic()
    {
        
        emotions.display(emotions.raisingElevator);
    }
}
