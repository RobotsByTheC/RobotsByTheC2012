/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands.dumper;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.templates.commands.CommandBase;

/**
 *
 * @author
 * peter
 */
public class CloseServoABitCommand extends CommandBase{

    Servo servo;
    
    public CloseServoABitCommand(Servo servo) {
        this.servo = servo;
    }

    protected void initialize() {
        servo.set(servo.get()-.05);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
