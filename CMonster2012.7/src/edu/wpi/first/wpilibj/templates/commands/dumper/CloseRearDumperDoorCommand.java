/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands.dumper;

import edu.wpi.first.wpilibj.templates.commands.CommandBase;

/**
 *
 * @author
 * peter
 */
public class CloseRearDumperDoorCommand extends CommandBase{

    public CloseRearDumperDoorCommand() {
        requires(dumperSubsystem);
    }

    protected void initialize() {
        dumperSubsystem.closeRearDoor();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return timeSinceInitialized()>.25;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
