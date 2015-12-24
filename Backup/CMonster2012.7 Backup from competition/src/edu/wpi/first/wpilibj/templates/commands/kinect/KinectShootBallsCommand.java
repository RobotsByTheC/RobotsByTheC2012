/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands.kinect;

import edu.wpi.first.wpilibj.templates.commands.CommandBase;
import edu.wpi.first.wpilibj.templates.commands.dumper.DumpBallsAndResetCommandGroup;

/**
 *
 * @author
 * peter
 */
public class KinectShootBallsCommand extends CommandBase
{
    private boolean beenPressed = false;
    
    protected void initialize() {
    }

    protected void execute() {
        if(oi.getKinectShifter().isInDrivePlusAccessoriesMode())
        {
            if(oi.getLeftArm().getRawButton(5) && beenPressed == false)
            {
                (new DumpBallsAndResetCommandGroup()).start();
                beenPressed = true;
            }
            
            if(!oi.getLeftArm().getRawButton(4))
                beenPressed = false;
        }
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
