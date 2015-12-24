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
public class LowerDumperRampCommand extends CommandBase{
    private double startTime;  
    public LowerDumperRampCommand(){
        requires(dumperSubsystem);
    }

    protected void initialize() {
        startTime=Timer.getFPGATimestamp();
        dumperSubsystem.lowerRamp();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        //return dumperSubsystem.rampLowered();
        return Timer.getFPGATimestamp()-startTime>0.5;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
