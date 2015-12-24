/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands.ramp;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author Halloran Film Studio
 */
public class LowerRampAndHoldDownCommandGroup extends CommandGroup{

    public LowerRampAndHoldDownCommandGroup() {
        addSequential(new LowerRampControllerCommand());
        addSequential(new HoldDownRampControllerCommand());
    }
    
}
