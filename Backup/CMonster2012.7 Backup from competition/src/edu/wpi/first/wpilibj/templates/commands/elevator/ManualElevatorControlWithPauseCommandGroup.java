/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands.elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.templates.commands.WaitCommandRequiresDriveBaseSubsystem;

/**
 *
 * @author Sean Halloran
 */
public class ManualElevatorControlWithPauseCommandGroup extends CommandGroup{

    public ManualElevatorControlWithPauseCommandGroup() {
        addSequential(new ManualElevatorOperationCommand());
        addSequential(new WaitCommandRequiresDriveBaseSubsystem(.4));
    }
    
    
    
}
