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
public class DriveDistanceWithEncodersCommand extends CommandBase{
    
    private double distance, speed;
    
    public DriveDistanceWithEncodersCommand(double distance, double speed){
        requires(driveBaseSubsystem);
        this.distance=distance;
        this.speed=speed;
    }

    protected void initialize() {
        driveBaseSubsystem.getBestRobotDrive().drive(speed ,0);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return (sensors.getAverageEncoderDistance()>=distance);
    }

    protected void end() {
        //driveBaseSubsystem.getBestRobotDrive().Drive(0, 0);
    }

    protected void interrupted() {
        //driveBaseSubsystem.getBestRobotDrive().Drive(0, 0);
    }
    
}
