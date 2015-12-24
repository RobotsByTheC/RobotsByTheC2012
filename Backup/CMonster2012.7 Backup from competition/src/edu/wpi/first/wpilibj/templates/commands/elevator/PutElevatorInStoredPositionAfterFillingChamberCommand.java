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
public class PutElevatorInStoredPositionAfterFillingChamberCommand extends CommandBase{

    public static final double storedHeight = 1;
    private final double runTime = 1.3;
    
    public PutElevatorInStoredPositionAfterFillingChamberCommand(){
        requires(elevatorSubsystem);
    }
    
    protected void initialize(){
        //elevatorSubsystem.setMotorSpeed((elevatorSubsystem.getElevatorHeight()>storedHeight)?-1.0:1.0);
        elevatorSubsystem.setMotorSpeed(-1);
    }

    protected void execute() {
    }

    protected boolean isFinished(){
        return timeSinceInitialized()>runTime;
    }

    protected void end() {
        elevatorSubsystem.setMotorSpeed(0);
        //elevatorSubsystem.setPutInStoredPosition(true);
    }

    protected void interrupted() {
        elevatorSubsystem.setMotorSpeed(0);
    }
    
}
