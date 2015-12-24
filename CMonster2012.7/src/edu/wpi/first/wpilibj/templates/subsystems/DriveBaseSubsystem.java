/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.commands.driving.ArcadeDriveCommand;

/**
 *
 * @author Halloran Film Studio
 */
public class DriveBaseSubsystem extends Subsystem{
    private BestRobotDrive drive;
    
    public DriveBaseSubsystem(){
        super();
        drive = new BestRobotDrive(RobotMap.Ports.PWM.jaguarFrontLeft,
                                   RobotMap.Ports.PWM.jaguarRearLeft,
                                   RobotMap.Ports.PWM.jaguarFrontRight,
                                   RobotMap.Ports.PWM.jaguarRearRight);
    }
    
    public void initDefaultCommand(){
        this.setDefaultCommand(new ArcadeDriveCommand());
    }
    
    public BestRobotDrive getBestRobotDrive(){
        return drive;
    }
    
}
