/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands.dumper;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.templates.commands.elevator.SetElevatorPutInStoredPositionCommand;

/**
 *
 * @author Sean Halloran
 */
public class DumpBallsAndResetCommandGroup extends CommandGroup{
    
    public DumpBallsAndResetCommandGroup(){
        addSequential(new OpenRearDumperDoorCommand());
        addSequential(new RaiseDumperRampCommand());
        addSequential(new OpenDumperDoorCommand());
        addSequential(new WaitForBallsToEvacuateCommand());
        addSequential(new WaitCommand(1));
        addSequential(new CloseDumperDoorCommand());
        addSequential(new LowerDumperRampCommand());
        addSequential(new SetElevatorPutInStoredPositionCommand(false));
    }
    
}
