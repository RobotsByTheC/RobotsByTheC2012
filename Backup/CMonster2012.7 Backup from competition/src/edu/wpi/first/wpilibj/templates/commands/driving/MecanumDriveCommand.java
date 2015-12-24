/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands.driving;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.commands.CommandBase;

/**
 *
 * @author Halloran Film Studio
 */
public class MecanumDriveCommand extends CommandBase{
    
//    private double updateTime;

    public MecanumDriveCommand(){
        requires(driveBaseSubsystem);
        //setInterruptible(false);
    }
    
    protected void initialize() {
    }

    protected void execute() {
        //CommandBase.driveBaseSubsystem.getBestRobotDrive().mecanumDriveXY(oi.getJoystick().getX(), oi.getJoystick().getY());
        driveBaseSubsystem.getBestRobotDrive().mecanumDriveXY((oi.isJoystickYInverted())?oi.getJoystickX():-oi.getJoystickX(), oi.getJoystickY());
  //      SmartDashboard.putDouble("Update Time", Timer.getFPGATimestamp()-updateTime);
  //      updateTime=Timer.getFPGATimestamp();
    }

    protected boolean isFinished() {
        return //!oi.getStick().getRawButton(RobotMap.Buttons.mecanumModeJoystickButton);
                false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
