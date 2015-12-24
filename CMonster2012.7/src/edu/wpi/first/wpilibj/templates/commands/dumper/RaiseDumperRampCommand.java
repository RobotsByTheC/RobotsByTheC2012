/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands.dumper;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.templates.commands.CommandBase;

/**
 *
 * @author
 * peter
 */
public class RaiseDumperRampCommand extends CommandBase{
    private double startTime;
    public RaiseDumperRampCommand(){
        requires(dumperSubsystem);
    }

    protected void initialize() {
        startTime=Timer.getFPGATimestamp();
        dumperSubsystem.raiseRamp();
    }

    protected void execute() {
    }

    protected boolean isFinished(){
        //return dumperSubsystem.rampRaised();
        return Timer.getFPGATimestamp()-startTime>0.15;
    }

    protected void end() {
    }

    protected void interrupted(){
        //dumperSubsystem.lowerRamp();
    }
    
    
    
}
