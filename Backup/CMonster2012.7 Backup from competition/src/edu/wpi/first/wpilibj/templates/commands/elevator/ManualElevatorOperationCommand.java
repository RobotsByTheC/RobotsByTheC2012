/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands.elevator;

import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.commands.CommandBase;

/**
 *
 * @author Halloran Film Studio
 */
public class ManualElevatorOperationCommand extends CommandBase{
    public ManualElevatorOperationCommand(){
        this.requires(elevatorSubsystem);
        this.requires(driveBaseSubsystem);
    }

    protected void initialize() {
        driveBaseSubsystem.getBestRobotDrive().drive(0, 0);
    }

    protected void execute(){
        elevatorSubsystem.setMotorSpeed(oi.getUninvertedJoystickY());
    }

    protected boolean isFinished(){
        return !CommandBase.oi.getStick().getRawButton(RobotMap.Buttons.manualElevatorOperationButton);
    }

    protected void end(){
    }

    protected void interrupted() {
    }
    
}
