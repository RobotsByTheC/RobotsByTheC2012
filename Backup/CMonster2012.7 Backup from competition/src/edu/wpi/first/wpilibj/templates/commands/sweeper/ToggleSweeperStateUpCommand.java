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
public class ToggleSweeperStateUpCommand extends CommandBase{
    public ToggleSweeperStateUpCommand(){
        this.requires(sweeperSubsystem);
    }

    protected void initialize(){
        if(sweeperSubsystem.getState()==SweeperSubsystem.SweeperState.stopped)
            sweeperSubsystem.setSweeperState(SweeperSubsystem.SweeperState.spitting);
        else if(sweeperSubsystem.getState()==SweeperSubsystem.SweeperState.sucking)
            sweeperSubsystem.setSweeperState(SweeperSubsystem.SweeperState.stopped);
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
