/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 * @author Sean Halloran
 */
public class WaitCommandRequiresDriveBaseSubsystem extends WaitCommand{

    public WaitCommandRequiresDriveBaseSubsystem(double timeout) {
        super(timeout);
        requires(CommandBase.driveBaseSubsystem);
    }

    protected void initialize() {
        super.initialize();
        CommandBase.driveBaseSubsystem.getBestRobotDrive().drive(0, 0);
    }
        
    
}
