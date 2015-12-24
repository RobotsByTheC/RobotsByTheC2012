/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.commands.ClawDoNothing;

/**
 *
 * @author Alex
 */
public class Claw extends Subsystem {
    Victor motor;
    // Initialize your subsystem here
    public Claw() {
        motor = new Victor(7);
    }
    
    public void initDefaultCommand() {
        setDefaultCommand(new ClawDoNothing());
    }
    
    public void move(double speed) {
        motor.set(speed);
    }
}
