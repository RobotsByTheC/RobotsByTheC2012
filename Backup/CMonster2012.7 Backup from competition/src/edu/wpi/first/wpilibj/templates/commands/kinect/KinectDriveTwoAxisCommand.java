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
public class KinectDriveTwoAxisCommand extends CommandBase{

    public KinectDriveTwoAxisCommand(){
        requires(driveBaseSubsystem);
    }      
            
    protected void initialize() {
    }

    protected void execute() {
        if(oi.getKinectShifter().isInDriveOnlyMode())
            driveBaseSubsystem.getBestRobotDrive().arcadeDrive(oi.getRightArm().getY(), oi.getLeftArm().getY());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
