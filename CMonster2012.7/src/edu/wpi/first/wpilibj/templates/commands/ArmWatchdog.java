/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.commands.ramp.RaiseRampControllerCommand;

/**
 *
 * @author Halloran Film Studio
 */
public class ArmWatchdog extends CommandBase{
    
    private Subsystem extendedSubsystem;
    private boolean on;

    public ArmWatchdog() {
        //armExtended = false;
        extendedSubsystem = null;
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }
    
    public boolean safeToExtend(Subsystem subsystem){
        //return (oi.overrideEnabled() || (extendedSubsystem == null || extendedSubsystem.equals(subsystem)));
        return true;
        
    }
    
    public Subsystem getExtendedSubsystem(){
        return extendedSubsystem;
    }

    protected void initialize() {
    }

    protected void execute() {
      if(on){
        boolean elevatorExtended = elevatorSubsystem.extendedBeyondFrame(),
                rampControllerExtended = rampControllerSubsystem.extendedBeyondFrame();
        if(elevatorExtended){
            extendedSubsystem = elevatorSubsystem;
            armWatchdogStatus("Elevator extended");
        }
        else if(rampControllerExtended){
            extendedSubsystem = rampControllerSubsystem;
            armWatchdogStatus("Ramp controller extended");
        }
        else{
            extendedSubsystem = null;
            armWatchdogStatus("No arms extended");
        }
        if(elevatorExtended && rampControllerExtended){
            armWatchdogStatus("Two Arms Extended! Retracting Ramp Controller");
            if(!oi.overrideEnabled() && !(rampControllerSubsystem.getCurrentCommand() instanceof RaiseRampControllerCommand)){
                (new RaiseRampControllerCommand()).start();
            }
        }
      }
    }
    
    private void armWatchdogStatus(String status){
        SmartDashboard.putString("Arm Watchdog", status);
    }

    protected boolean isFinished(){
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
