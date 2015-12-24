/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands.dumper;

import edu.wpi.first.wpilibj.templates.commands.CommandBase;

/**
 *
 * @author Sean Halloran
 */
public class DoorServoManualControl extends CommandBase
{
    private boolean buttonPressed = false; // false = 8, true = 9
    
    public DoorServoManualControl(boolean button)
    {
        buttonPressed = button;
    }
    
    protected void initialize()
    {
    }

    protected void execute()
    {
        if(!buttonPressed){}
            dumperSubsystem.setRampServo1Position(dumperSubsystem.getRampServo1Position() - 0.005);
        if(buttonPressed){}
            dumperSubsystem.setRampServo1Position(dumperSubsystem.getRampServo1Position() + 0.005);
    }

    protected boolean isFinished()
    {
        return true;
    }

    protected void end()
    {
    }

    protected void interrupted()
    {
    }
    
}
