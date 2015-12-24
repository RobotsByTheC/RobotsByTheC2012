/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands.ramp;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.commands.CommandBase;

/**
 *
 * @author Halloran Film Studio
 */
public class LowerRampControllerCommand extends CommandBase{
    
//    boolean unfolded;

    public LowerRampControllerCommand(){
        requires(rampControllerSubsystem);
//        safe = true;
//        unfolded = false;
    }
    
    protected void initialize() {
        //rampControllerSubsytem.lower();
//        if(rampControllerSubsystem.isDown())
//            unfolded=true;
        //rampControllerSubsytem.toggleUnfolded();
        rampControllerSubsystem.setUnfolded(true);
        SmartDashboard.putString("Solenoid", "Retracted");
    }

    protected void execute() {
//        if(!RobotTemplate.armWatchdog.safeToExtend(sweeperSubsystem)){
//            safe = false;
//            (new RaiseRampControllerCommand()).start();
//        }
//        if(rampControllerSubsystem.isDown() && !unfolded)
//            unfolded = true;
//        if(unfolded){
//            rampControllerSubsystem.holdDown();
//        }
//        else
//            rampControllerSubsystem.lower();
        rampControllerSubsystem.lower();
//        if(unfolded && !rampControllerSubsystem.isDown())
//            rampControllerSubsystem.holdDown();
//        else if(!rampControllerSubsystem.isDown())
//            rampControllerSubsystem.lower();
//        else
//            rampControllerSubsystem.stop();
    }

    protected boolean isFinished() {
        return rampControllerSubsystem.isDown()&&!rampControllerSubsystem.isUp();
        //return false;
    }

    protected void end() {
        rampControllerSubsystem.stop();
        rampControllerSubsystem.extendSolenoid();
        SmartDashboard.putString("Solenoid", "Extended");
        
    }

    protected void interrupted(){
        rampControllerSubsystem.stop();
    }
    
}
