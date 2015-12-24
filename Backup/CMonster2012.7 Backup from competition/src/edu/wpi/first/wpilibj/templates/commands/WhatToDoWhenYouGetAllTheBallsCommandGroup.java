/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.templates.commands.dumper.CloseRearDumperDoorCommand;
import edu.wpi.first.wpilibj.templates.commands.elevator.PutElevatorInStoredPositionAfterFillingChamberCommand;
import edu.wpi.first.wpilibj.templates.commands.elevator.SetElevatorPutInStoredPositionCommand;
import edu.wpi.first.wpilibj.templates.commands.sweeper.SweeperStopCommand;

/**
 *
 * @author
 * peter
 */
public class WhatToDoWhenYouGetAllTheBallsCommandGroup extends CommandGroup{

    public WhatToDoWhenYouGetAllTheBallsCommandGroup() {
        addParallel(new SweeperStopCommand());
        addSequential(new CloseRearDumperDoorCommand());
        addSequential(new PutElevatorInStoredPositionAfterFillingChamberCommand());
        addSequential(new SetElevatorPutInStoredPositionCommand(true));
    }
    
    
    
}
