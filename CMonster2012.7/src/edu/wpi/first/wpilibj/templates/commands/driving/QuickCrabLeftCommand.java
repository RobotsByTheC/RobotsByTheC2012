/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands.driving;

//import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.commands.CommandBase;
import edu.wpi.first.wpilibj.templates.subsystems.BestRobotDrive;

/**
 *
 * @author Halloran Film Studio
 */
public class QuickCrabLeftCommand extends CommandBase{

    protected void initialize() {
    }

    protected void execute() {
        driveBaseSubsystem.getBestRobotDrive().crabRight((oi.isJoystickYInverted())?-BestRobotDrive.quickCrabSpeed:BestRobotDrive.quickCrabSpeed);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
