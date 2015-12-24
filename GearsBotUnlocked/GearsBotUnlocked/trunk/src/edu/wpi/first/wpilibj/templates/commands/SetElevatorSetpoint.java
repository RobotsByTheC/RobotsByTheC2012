/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

/**
 *
 * @author Alex
 */
public class SetElevatorSetpoint extends CommandBase {
    private double setpoint;
    
    public SetElevatorSetpoint(double setpoint) {
        requires(elevator);
        this.setpoint = setpoint;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        elevator.setSetpoint(setpoint);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(elevator.getPosition() - setpoint) < .05;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
