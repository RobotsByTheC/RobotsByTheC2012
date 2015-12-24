/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands.kinect;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.templates.commands.CommandBase;

/**
 *
 * @author team 2084
 */
public class KinectShifterUpdateCommand extends CommandBase
{
    protected void initialize()
    {
    }

    protected void execute()
    {
        CommandBase.oi.getKinectShifter().update();
        if(CommandBase.oi.getKinectShifter().isInDriveOnlyMode() && !(driveBaseSubsystem.getCurrentCommand() instanceof KinectDriveTwoAxisCommand))
            (new KinectDriveTwoAxisCommand()).start();
        else if(CommandBase.oi.getKinectShifter().isInDriveOnlyMode() && !(driveBaseSubsystem.getCurrentCommand() instanceof KinectDriveOneAxisCommand))
            (new KinectDriveOneAxisCommand()).start();
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
