/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands.sweeper;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.commands.CommandBase;

/**
 *
 * @author
 * peter
 */
public class SweeperMonitorCommand extends CommandBase{

    public SweeperMonitorCommand(){
        this.requires(sweeperSubsystem);
    }
    
    protected void initialize() {
    }

    protected void execute() {
        SmartDashboard.putString("Sweeper State: ", sweeperSubsystem.getState().printState());
//        if(!oi.overrideEnabled() && sensors.chamberFilled())
//            (new SweeperStopCommand()).start();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
