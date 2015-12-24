/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands.dumper;

import edu.wpi.first.wpilibj.templates.commands.CommandBase;

/**
 *
 * @author Halloran Film Studio
 */
public class DumperMonitorCommand extends CommandBase{
    
    public DumperMonitorCommand(){
        requires(dumperSubsystem);
    }

    protected void initialize() {
    }

    protected void execute() {
//        if(!oi.overrideEnabled() && sensors.chamberFilled()){
//            (new RaiseDumperRampCommand()).start();
//        }
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
