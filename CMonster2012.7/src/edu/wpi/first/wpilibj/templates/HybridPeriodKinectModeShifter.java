/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.KinectStick;
import edu.wpi.first.wpilibj.templates.commands.CommandBase;

/**
 *
 * @author team 2084
 */
public class HybridPeriodKinectModeShifter
{
    KinectStick kStick1;
    KinectStick kStick2;
    
    private int currentCommand;
    /// mode 0:
    /* 0 = using the elevator with arcade drive, only forward and back (one-axis), sweeper, ramp lowerer
     * elevator     --> left arm
     * wheels       --> right arm (arcade)
     * sweeper      --> left foot out to the left = toggle
     * ramp lowerer --> right foot out to the right = toggle
    */
    
    /// mode 1:
    /* 1 = using the drive subsystem (only) with arcade drive (two-axis) (KinectDriveCommand)
     * wheels       --> left + right arm (left = y axis (power), right = x axis (steering))
     */

    
    public HybridPeriodKinectModeShifter()
    {
        
        currentCommand = 1;
    }
    
    public void update()
    {
        kStick1 = CommandBase.oi.getLeftArm();
        kStick2 = CommandBase.oi.getRightArm();
        //// only change the mode if the sticks are near 'neutral'
        if(Math.abs(kStick1.getY()) <= 0.1 && Math.abs(kStick2.getY()) <= 0.1)
        {
            //// head to the left
            if(kStick1.getRawButton(2) && currentCommand > 0)
            {
                currentCommand--;
            }
            
            //// head to the right
            if(kStick1.getRawButton(1) && currentCommand < 1)
            {
                currentCommand++;
            }
        }
    }
    
    public boolean isInDriveOnlyMode()
    {
        return (currentCommand == 1);
    }
    
    public boolean isInDrivePlusAccessoriesMode()
    {
        return (currentCommand == 0);
    }
}
