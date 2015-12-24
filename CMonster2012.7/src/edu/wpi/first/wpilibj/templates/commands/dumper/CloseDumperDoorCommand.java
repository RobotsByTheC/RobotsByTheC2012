/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands.dumper;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.templates.commands.CommandBase;

/**
 *
 * @author Sean Halloran
 */
public class CloseDumperDoorCommand extends CommandBase{
    private double startTime;
    public CloseDumperDoorCommand(){
        requires(dumperSubsystem);
    }

    protected void initialize() {
        startTime=Timer.getFPGATimestamp();
        dumperSubsystem.closeDoor();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        //return dumperSubsystem.doorClosed();
        return Timer.getFPGATimestamp()-startTime>0.5;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
    
    
}
