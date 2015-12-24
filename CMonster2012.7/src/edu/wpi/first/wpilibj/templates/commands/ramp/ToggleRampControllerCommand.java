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
public class ToggleRampControllerCommand extends CommandBase{

    protected void initialize() {
//        if(rampControllerSubsystem.getCurrentCommand() instanceof RampControllerDoNothing){
//            if(rampControllerSubsystem.isDown())
//                (new RaiseRampControllerCommand()).start();
//            else
//                (new LowerRampControllerCommand()).start();
//        }
//        if(rampControllerSubsystem.getCurrentCommand() instanceof LowerRampControllerCommand)
//            (new RaiseRampControllerCommand()).start();
//        else if(rampControllerSubsystem.getCurrentCommand() instanceof RaiseRampControllerCommand)
//            (new LowerRampControllerCommand()).start();
        if(rampControllerSubsystem.getUnfolded())
            (new RaiseRampControllerCommand()).start();
        else
            (new LowerRampControllerCommand()).start();
            
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
