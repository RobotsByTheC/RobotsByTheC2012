/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands.elevator;

import edu.wpi.first.wpilibj.templates.commands.CommandBase;

/**
 *
 * @author Ben
 */
public class StopElevatorCommand extends CommandBase{
    public StopElevatorCommand(){
        requires(elevatorSubsystem);
    }
    protected void initialize() {
    }

    protected void execute(){
        elevatorSubsystem.setMotorSpeed(0);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
