/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.templates.subsystems.Elevator;
import edu.wpi.first.wpilibj.templates.subsystems.Wrist;

/**
 *
 * @author Alex
 */
public class PlaceSoda extends CommandGroup {

    public PlaceSoda() {
        addParallel(new SetElevatorSetpoint(Elevator.TABLE_HEIGHT));
        addSequential(new SetWristSetpoint(Wrist.PICKUP));
        addSequential(new OpenClaw());
    }
    
}
