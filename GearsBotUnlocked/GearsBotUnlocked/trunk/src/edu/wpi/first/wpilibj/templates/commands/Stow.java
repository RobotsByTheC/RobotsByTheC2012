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
public class Stow extends CommandGroup {
    
    public Stow() {
        addParallel(new CloseClaw());
        addParallel(new SetElevatorSetpoint(Elevator.STOW));
        addParallel(new SetWristSetpoint(Wrist.STOW));
    }
}
