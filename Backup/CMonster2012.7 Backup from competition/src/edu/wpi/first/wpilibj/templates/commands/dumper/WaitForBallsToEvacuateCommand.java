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
public class WaitForBallsToEvacuateCommand extends CommandBase{

    protected void initialize() {
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return sensors.chamberEmpty();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
