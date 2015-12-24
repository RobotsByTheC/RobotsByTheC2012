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
public class ToggleDumperDoorCommand extends CommandBase{
    
    boolean done;

    public ToggleDumperDoorCommand(){
        done = false;
    }
    
    protected void initialize(){
       
    }

    protected void execute() {
        if(dumperSubsystem.getCurrentCommand() instanceof DumperMonitorCommand){
            if(dumperSubsystem.doorClosed()){
                (new OpenDumperDoorCommand()).start();
                done = true;
            }
            else{
                (new CloseDumperDoorCommand()).start();
                done = true;
            }
        }
        else if (dumperSubsystem.getCurrentCommand() instanceof OpenDumperDoorCommand){
            (new CloseDumperDoorCommand()).start();
            done = true;
        }
        else if (dumperSubsystem.getCurrentCommand() instanceof CloseDumperDoorCommand){
            (new OpenDumperDoorCommand()).start();
            done = true;
        }
    }

    protected boolean isFinished() {
        return done;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
