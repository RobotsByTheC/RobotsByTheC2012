/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands.ramp;

//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.smartdashboardimport edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.commands.CommandBase;

/**
 *
 * @author Sean Halloran
 */
public class ManualRampControlCommand extends CommandBase{

    public ManualRampControlCommand(){
        requires(rampControllerSubsystem);
        requires(elevatorSubsystem);
    }
    
    protected void initialize() {
        //driveBaseSubsystem.getBestRobotDrive().Drive(0, 0);
        elevatorSubsystem.setMotorSpeed(0);
    }

    protected void execute() {
        double y = oi.getTestStick().getY();
        //y = (y>0) ? y*y : -y*y;
        rampControllerSubsystem.spin(y);
        //SmartDashboard.putDouble("RampSpeed", y);
        
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
