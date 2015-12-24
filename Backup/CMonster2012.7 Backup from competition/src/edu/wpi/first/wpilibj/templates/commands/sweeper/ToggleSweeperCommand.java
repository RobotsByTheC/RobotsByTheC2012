/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands.sweeper;

import edu.wpi.first.wpilibj.templates.commands.CommandBase;
import edu.wpi.first.wpilibj.templates.subsystems.SweeperSubsystem;

/**
 *
 * @author
 * peter
 */
public class ToggleSweeperCommand extends CommandBase{

    protected void initialize() {
        if(sweeperSubsystem.getState()==SweeperSubsystem.SweeperState.sucking)
            (new SweeperStopCommand()).start();
        else
            (new SweeperSuckCommand()).start();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
