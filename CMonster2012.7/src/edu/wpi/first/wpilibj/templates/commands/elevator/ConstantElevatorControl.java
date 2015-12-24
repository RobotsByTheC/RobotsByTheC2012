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
public class ConstantElevatorControl extends CommandBase{

    public ConstantElevatorControl() {
        requires(elevatorSubsystem);
    }

    
    
    protected void initialize() {
    }

    protected void execute() {
        elevatorSubsystem.setMotorSpeed(oi.getTestStick().getY());
    }

    protected boolean isFinished() {
        return false;
        
    }

    protected void end() {
        elevatorSubsystem.setMotorSpeed(0);
    }

    protected void interrupted() {
        elevatorSubsystem.setMotorSpeed(0);
    }
    
}
