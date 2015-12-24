/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

/**
 *
 * @author
 * peter
 */
public class DriveForwardTimeout extends CommandBase{

    protected void initialize() {
    }

    protected void execute() {
        CommandBase.driveBaseSubsystem.getBestRobotDrive().arcadeDrive(.43, 0.0);
    }

    protected boolean isFinished() {
        return timeSinceInitialized()>.95;
    }

    protected void end() {
        CommandBase.driveBaseSubsystem.getBestRobotDrive().arcadeDrive(0, 0.0);
    }

    protected void interrupted() {
    }
    
}
