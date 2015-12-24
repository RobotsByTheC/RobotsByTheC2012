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
public class KinectDriveOneAxisCommand extends CommandBase{

    public KinectDriveOneAxisCommand(){
        requires(driveBaseSubsystem);
    }      
            
    protected void initialize() {
    }

    protected void execute() {
        if(oi.getKinectShifter().isInDrivePlusAccessoriesMode())
            driveBaseSubsystem.getBestRobotDrive().arcadeDrive(0, oi.getLeftArm().getY());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
