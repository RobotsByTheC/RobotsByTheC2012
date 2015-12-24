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
public class RampControllerDoNothingCommand extends CommandBase{

    public RampControllerDoNothingCommand()
    {
        requires(rampControllerSubsystem);
    }
    
    protected void initialize() {
    }

    protected void execute() {
        rampControllerSubsystem.stop();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
