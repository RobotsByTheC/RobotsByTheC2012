/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands.driving;

import edu.wpi.first.wpilibj.templates.commands.CommandBase;

/**
 *
 * @author
 * peter
 */
public class DriveUntilStoppedCommand extends CommandBase{

    private final double speed = .52;
    
    protected void initialize() {
    }

    protected void execute() {
        //driveBaseSubsystem.getBestRobotDrive().jaguarDrive(speed, -speed, speed, speed);
        CommandBase.driveBaseSubsystem.getBestRobotDrive().arcadeDrive(speed, 0.0);
        //driveBaseSubsystem.getBestRobotDrive().jaguarDrive(speed, speed, speed, speed);
    }

    protected boolean isFinished() {
        return oi.getStick().getRawButton(9);
    }

    protected void end() {
        driveBaseSubsystem.getBestRobotDrive().arcadeDrive(0,0);
    }

    protected void interrupted() {
    }
    
}
