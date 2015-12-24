/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.commands.DriveWithJoysticks;

/**
 *
 */
public class DriveTrain extends PIDSubsystem {
    private static final double Kp = 3;
    private static final double Ki = .2;
    private static final double Kd = 0.0;
    
    RobotDrive drive;
    AnalogChannel rangefinder;


    // Initialize your subsystem here
    public DriveTrain() {
        super("DriveTrain", Kp, Ki, Kd);
        drive = new RobotDrive(2, 1);
        
        rangefinder = new AnalogChannel(3);
    }
    
    public void initDefaultCommand() {
        setDefaultCommand(new DriveWithJoysticks());
    }

    protected double returnPIDInput() {
        return rangefinder.getVoltage();
    }

    protected void usePIDOutput(double output) {
        tankDrive(output, output);
    }
    
    public void tankDrive(double left, double right) {
        drive.tankDrive(left, right);
    }
    
    public void status () {
        SmartDashboard.putDouble("Range Finder", rangefinder.getVoltage());
    }
}

