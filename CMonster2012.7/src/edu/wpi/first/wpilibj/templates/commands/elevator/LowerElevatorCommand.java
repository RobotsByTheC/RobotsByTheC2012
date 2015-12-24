/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands.elevator;

import edu.wpi.first.wpilibj.templates.commands.CommandBase;

/**
 *
 * @author Sean Halloran
 */
public class LowerElevatorCommand extends CommandBase{

    public LowerElevatorCommand(){
        requires(elevatorSubsystem);
    }
    
    protected void initialize(){
        elevatorSubsystem.setMotorSpeed(.5);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        //return elevatorSubsystem.atGround();
        return timeSinceInitialized()>6;
    }

    protected void end() {
        elevatorSubsystem.setMotorSpeed(0);
    }

    protected void interrupted() {
        elevatorSubsystem.setMotorSpeed(0);
    }
    
}
