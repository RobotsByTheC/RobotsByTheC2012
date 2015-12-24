/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 *
 * @author Alex
 */
public class Wrist extends PIDSubsystem {
    private static final double Kp = -2;
    private static final double Ki = 0.0;
    private static final double Kd = 0.0;
    
    public static final double PICKUP = 3.5,
            STOW = 1.75;
    
    Victor motor;
    AnalogChannel pot;

    // Initialize your subsystem here
    public Wrist() {
        super("Wrist", Kp, Ki, Kd);
        motor = new Victor(5);
        pot = new AnalogChannel(2);
        
        setSetpoint(STOW);
        enable(); // - Enables the PID controller.
    }
    
    protected double returnPIDInput() {
        return pot.getVoltage();
    }
    
    protected void usePIDOutput(double output) {
        motor.set(output);
    }
    
    public void status() {
        SmartDashboard.putDouble("Wrist Position", pot.getVoltage());
        SmartDashboard.putDouble("Wrist Setpoint", this.getSetpoint());
    }
}
