/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.OI;
import edu.wpi.first.wpilibj.templates.subsystems.Claw;
import edu.wpi.first.wpilibj.templates.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.templates.subsystems.Elevator;
import edu.wpi.first.wpilibj.templates.subsystems.Wrist;

/**
 *
 * @author Alex
 */
public abstract class CommandBase extends Command {
    public static OI oi;
    public static DriveTrain drivetrain = new DriveTrain();
    public static Elevator elevator = new Elevator();
    public static Wrist wrist = new Wrist();
    public static Claw claw = new Claw();
    
    public static void init() {
        oi = new OI();
        drivetrain.initDefaultCommand();
        claw.initDefaultCommand();
        
        SmartDashboard.putData(drivetrain);
        SmartDashboard.putData(elevator);
        SmartDashboard.putData(wrist);
        SmartDashboard.putData(claw);
    }
    
    public CommandBase(String name) { super(name); }
    public CommandBase() { super(); }
}
