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
public class ToggleDumperRampCommand extends CommandBase{

    boolean done;

    public ToggleDumperRampCommand(){
        done = false;
    }
    
    protected void initialize(){
       
    }

    protected void execute() {
        if(dumperSubsystem.getCurrentCommand() instanceof DumperMonitorCommand){
            if(dumperSubsystem.rampLowered()){
                (new RaiseDumperRampCommand()).start();
                done = true;
            }
            else{
                (new LowerDumperRampCommand()).start();
                done = true;
            }
        }
        else if (dumperSubsystem.getCurrentCommand() instanceof RaiseDumperRampCommand){
            (new LowerDumperRampCommand()).start();
            done = true;
        }
        else if (dumperSubsystem.getCurrentCommand() instanceof LowerDumperRampCommand){
            (new RaiseDumperRampCommand()).start();
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
