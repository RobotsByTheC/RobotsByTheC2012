/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands.elevator;

import edu.wpi.first.wpilibj.templates.commands.CommandBase;
import edu.wpi.first.wpilibj.templates.commands.WhatToDoWhenYouGetAllTheBallsCommandGroup;
import edu.wpi.first.wpilibj.templates.commands.dumper.RaiseDumperRampCommand;
import edu.wpi.first.wpilibj.templates.commands.sweeper.SweeperStopCommand;

/**
 *
 * @author
 * peter
 */
public class ElevatorBallMonitorCommand extends CommandBase{

    private boolean lastElevatorChamberFilled;
    
    protected void initialize() {
        lastElevatorChamberFilled = false;
    }

    protected void execute() {
        //if(!oi.overrideEnabled() && elevatorSubsystem.isHeightCalibrated() && sensors.chamberFilled() && !(elevatorSubsystem.getCurrentCommand() instanceof PutElevatorInStoredPositionAfterFillingChamberCommand) && !(elevatorSubsystem.getCurrentCommand() instanceof RaiseElevatorCommand) && elevatorSubsystem.getElevatorHeight() < PutElevatorInStoredPositionAfterFillingChamberCommand.storedHeight - 2)
        if(!oi.overrideEnabled() && sensors.chamberFilled()!=lastElevatorChamberFilled && sensors.chamberFilled() && !elevatorSubsystem.isPutInStoredPosition())
            //(new PutElevatorInStoredPositionAfterFillingChamberCommand()).start();
            (new WhatToDoWhenYouGetAllTheBallsCommandGroup()).start();
        lastElevatorChamberFilled = sensors.chamberFilled();
        /*
         * if:
         * operator override is off
         * the height has been calibrated
         * the camber is filled with balls
         * the elevator is not already putting itself in the stored position
         * the camber is below the stored position
         * the elevator is not running the raise elevator command
         */
//        if(!oi.overrideEnabled() && sensors.chamberFilled())
//            (new SweeperStopCommand()).start();
//        if(!oi.overrideEnabled() && sensors.chamberFilled() && !dumperSubsystem.rampRaised())
//            (new RaiseDumperRampCommand()).start();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
