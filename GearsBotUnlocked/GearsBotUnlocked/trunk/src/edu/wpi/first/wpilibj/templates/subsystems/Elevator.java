/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Alex
 */
public class Elevator extends PIDSubsystem {
    private static final double Kp = 6;
    private static final double Ki = 0.0;
    private static final double Kd = 0.0;
    
    public static final double BOTTOM = 4.6,
            STOW = 1.65,
            TABLE_HEIGHT = 1.58;
    
    SpeedController motor;
    AnalogChannel pot;

    // Initialize your subsystem here
    public Elevator() {
        super("Shoulder", Kp, Ki, Kd);
        
        motor = new Jaguar(6);
        pot = new AnalogChannel(4);

        setSetpoint(STOW);
        enable();
    }
    
    protected double returnPIDInput() {
        return pot.getVoltage();
    }
    
    protected void usePIDOutput(double output) {
        motor.set(output);
    }
    
    public void status() {
        SmartDashboard.putDouble("Elevator Position", pot.getVoltage());
    }
}
