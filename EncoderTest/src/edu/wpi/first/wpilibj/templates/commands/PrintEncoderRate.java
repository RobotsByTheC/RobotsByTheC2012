/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Peter
 */
public class PrintEncoderRate extends CommandBase{

    Encoder encoder;
    
    protected void initialize() {
        encoder = new Encoder(1,2);
    }

    protected void execute() {
        SmartDashboard.putDouble("Encoder Rate", encoder.getRaw());
        SmartDashboard.putDouble("Encoder Value", encoder.get());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
    
    
}
