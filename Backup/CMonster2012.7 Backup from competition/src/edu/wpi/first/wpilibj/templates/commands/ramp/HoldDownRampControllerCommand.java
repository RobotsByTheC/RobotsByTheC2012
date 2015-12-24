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
public class HoldDownRampControllerCommand extends CommandBase{

    public HoldDownRampControllerCommand() {
        requires(rampControllerSubsystem);
    }
    
    protected void initialize() {
        rampControllerSubsystem.setUnfolded(true);
    }

    protected void execute() {
        rampControllerSubsystem.holdDown();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        rampControllerSubsystem.stop();
    }

    protected void interrupted() {
        rampControllerSubsystem.stop();
    }
    
}
