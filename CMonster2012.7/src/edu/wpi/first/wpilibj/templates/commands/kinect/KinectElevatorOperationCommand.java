/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands.kinect;

import edu.wpi.first.wpilibj.templates.commands.CommandBase;

/**
 *
 * @author
 * team 2084
 */
public class KinectElevatorOperationCommand extends CommandBase{

    public KinectElevatorOperationCommand()
    {
        requires(elevatorSubsystem);
    }
    
    protected void initialize()
    {
    }

    protected void execute()
    {
        if(oi.getKinectShifter().isInDrivePlusAccessoriesMode())
            elevatorSubsystem.setMotorSpeed(-(oi.getRightArm().getY()));
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
