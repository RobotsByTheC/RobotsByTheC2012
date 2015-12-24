/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands.elevator;

import edu.wpi.first.wpilibj.templates.commands.CommandBase;

/**
 *
 * @author
 * peter
 */
public class MoveElevatorDownCommand extends CommandBase{

    public MoveElevatorDownCommand() {
        requires(elevatorSubsystem);
    }

    protected void initialize() {
        elevatorSubsystem.setMotorSpeed(1);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return timeSinceInitialized()>5;
    }

    protected void end() {
        elevatorSubsystem.setMotorSpeed(0);
    }

    protected void interrupted() {
        elevatorSubsystem.setMotorSpeed(0);
    }
    
}
