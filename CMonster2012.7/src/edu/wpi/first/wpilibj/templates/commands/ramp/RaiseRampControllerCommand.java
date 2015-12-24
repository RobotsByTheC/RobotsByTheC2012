/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands.ramp;

import edu.wpi.first.wpilibj.templates.commands.CommandBase;

/**
 *
 * @author Halloran Film Studio
 */
public class RaiseRampControllerCommand extends CommandBase{

    public RaiseRampControllerCommand(){
        requires(rampControllerSubsystem);
    }
    
    protected void initialize() {
        //rampControllerSubsytem.raise();
        rampControllerSubsystem.setUnfolded(false);
        rampControllerSubsystem.retractSolenoid();
    }

    protected void execute() {
//        if(rampControllerSubsystem.isUp())
//            rampControllerSubsystem.stop();
//        else
        if(timeSinceInitialized()>.25)
            rampControllerSubsystem.raise();
        else
            rampControllerSubsystem.stop();
    }

    protected boolean isFinished(){
        return false;
        //return //rampControllerSubsytem.isUp();
        //        false;
    }

    protected void end() {
        rampControllerSubsystem.stop();
    }

    protected void interrupted() {
        rampControllerSubsystem.stop();
    }
    
}
