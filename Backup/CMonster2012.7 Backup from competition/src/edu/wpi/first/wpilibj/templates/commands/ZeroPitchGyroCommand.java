/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

/**
 *
 * @author Sean Halloran
 */
public class ZeroPitchGyroCommand extends CommandBase{

    protected void initialize(){
        sensors.getPitchGyro().reset();
    }

    protected void execute(){
    }

    protected boolean isFinished(){
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
