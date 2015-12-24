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
public class SetElevatorPutInStoredPositionCommand extends CommandBase{

    private boolean stored;
    
    public SetElevatorPutInStoredPositionCommand(boolean stored) {
        this.stored=stored;
    }

    protected void initialize() {
        elevatorSubsystem.setPutInStoredPosition(stored);
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
