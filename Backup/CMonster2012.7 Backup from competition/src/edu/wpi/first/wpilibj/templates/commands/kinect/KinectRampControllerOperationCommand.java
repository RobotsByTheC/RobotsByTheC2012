/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands.kinect;

import edu.wpi.first.wpilibj.templates.commands.CommandBase;
import edu.wpi.first.wpilibj.templates.commands.ramp.ToggleRampControllerCommand;

/**
 *
 * @author team 2084
 */
public class KinectRampControllerOperationCommand extends CommandBase
{
    private boolean beenPressed = false; // so this command doesn't spawn a zillion ToggleRampControllerCommands while the button is pressed
    
    public KinectRampControllerOperationCommand()
    {
        requires(rampControllerSubsystem);
    }
    
    protected void initialize()
    {
        
    }

    protected void execute()
    {
        if(oi.getKinectShifter().isInDrivePlusAccessoriesMode())
        {
            if(oi.getLeftArm().getRawButton(3) && beenPressed == false)
            {
                (new ToggleRampControllerCommand()).start();
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
