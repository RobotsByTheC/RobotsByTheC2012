/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;
//
//import edu.wpi.first.wpilibj.RobotDrive;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//import edu.wpi.first.wpilibj.command.Command;
//import edu.wpi.first.wpilibj.templates.commands.CommandBase;

/**
 *
 * @author Sean Halloran
 */
public class SwitchRobotFrontCommand extends CommandBase{

    protected void initialize() {
        oi.invertJoystickY();
        SmartDashboard.putString("Robot Front", (oi.isJoystickYInverted())?"Sweeper":"Ramp Controller");
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
    
}
