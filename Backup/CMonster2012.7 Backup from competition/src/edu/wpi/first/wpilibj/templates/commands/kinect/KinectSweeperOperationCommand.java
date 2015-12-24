/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands.kinect;

import edu.wpi.first.wpilibj.templates.commands.CommandBase;
import edu.wpi.first.wpilibj.templates.commands.sweeper.ToggleSweeperCommand;

/**
 *
 * @author team 2084
 */
public class KinectSweeperOperationCommand extends CommandBase
{
    private boolean beenPressed = false; // so this command doesn't spawn a zillion ToggleSweeperCommands while the button is pressed
    
    public KinectSweeperOperationCommand()
    {
        requires(sweeperSubsystem);
    }
    
    protected void initialize()
    {
    }

    protected void execute()
    {
        if(oi.getKinectShifter().isInDrivePlusAccessoriesMode())
        {
            if(oi.getLeftArm().getRawButton(4) && beenPressed == false)
            {
                (new ToggleSweeperCommand()).start();
                beenPressed = true;
            }
            
            if(!oi.getLeftArm().getRawButton(4))
                beenPressed = false;
        }
    }

    protected boolean isFinished()
    {
        return false;
    }

    protected void end()
    {
    }

    protected void interrupted()
    {
    }
    
}
