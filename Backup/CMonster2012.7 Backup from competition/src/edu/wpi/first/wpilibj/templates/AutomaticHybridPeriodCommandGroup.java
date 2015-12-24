/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.templates.commands.DriveForwardTimeout;
import edu.wpi.first.wpilibj.templates.commands.driving.DriveUntilSmashCommand;
import edu.wpi.first.wpilibj.templates.commands.dumper.DumpBallsAndResetCommandGroup;
import edu.wpi.first.wpilibj.templates.commands.dumper.RaiseDumperRampCommand;
import edu.wpi.first.wpilibj.templates.commands.elevator.LowerElevatorCommand;
import edu.wpi.first.wpilibj.templates.commands.elevator.RaiseElevatorCommand;

/**
 *
 * @author
 * team 2084
 */
public class AutomaticHybridPeriodCommandGroup extends CommandGroup{

    public AutomaticHybridPeriodCommandGroup() {
        addSequential(new RaiseDumperRampCommand());
        //addParallel(new RaiseDumperRampCommand());
        addSequential(new DriveUntilSmashCommand(true), 9.5);//I moved the timeout from within the command to here to make the code more concise
        addSequential(new DriveForwardTimeout());
        addSequential(new RaiseElevatorCommand());
        addSequential(new DumpBallsAndResetCommandGroup());
        //addParallel(new LowerElevatorCommand());
        addSequential(new LowerElevatorCommand());
        //addSequential(new HybridPeriodKinectCommandGroup());
    }
    
}
