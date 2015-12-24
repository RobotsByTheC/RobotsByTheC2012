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
public class SweeperSpitCommand extends CommandBase{

    public SweeperSpitCommand(){
        this.requires(sweeperSubsystem);
    }
    
    protected void initialize(){
        sweeperSubsystem.setSweeperState(SweeperSubsystem.SweeperState.spitting);
    }

    protected void execute() {
    }

    protected boolean isFinished(){
        return true;
    }
    
    protected void end() {
    }

    protected void interrupted() {
    }
    
}