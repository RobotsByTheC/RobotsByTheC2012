/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author Alex
 */
public class SodaDelivery extends CommandGroup {
    
    public SodaDelivery() {
        addSequential(new PrepareToGrab());
        addSequential(new Grab());
        addSequential(new DriveToDistance(.11));
        addSequential(new PlaceSoda());
        addSequential(new DriveToDistance(.2));
        addSequential(new Stow());
    }
}
