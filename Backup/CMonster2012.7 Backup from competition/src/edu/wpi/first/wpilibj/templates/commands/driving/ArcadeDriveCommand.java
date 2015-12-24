/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands.driving;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.commands.CommandBase;

/**
 *
 * @author Halloran Film Studio
 */
public class ArcadeDriveCommand extends CommandBase{

//    double updateTime;
    
    public ArcadeDriveCommand(){
        requires(CommandBase.driveBaseSubsystem); /// probably unecessary but I changed this to "CommandBase.driveBaseSubsystem")
        //this.setInterruptible(true);
    }
    
    protected void initialize() {
    }

    protected void execute() {
        driveBaseSubsystem.getBestRobotDrive().arcadeDrive(oi.getJoystickY(), oi.getJoystickX());
 //       SmartDashboard.putDouble("Update Time", Timer.getFPGATimestamp()-updateTime);
 //       updateTime=Timer.getFPGATimestamp();
    }

    protected boolean isFinished(){
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
        
    }
    
}
