/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.AnalogChannel;


/**
 *
 * @author Sean Halloran
 */
public class DigitalAnalogInput{
    
    AnalogChannel analogInput;
    final double digitalLimit = 4;
    
    public DigitalAnalogInput(int analogPort){
        analogInput = new AnalogChannel(2, analogPort);
    }
    
    public boolean get(){
        return (analogInput.getAverageVoltage()>digitalLimit);
    }
    public double getVoltage(){
        return analogInput.getAverageVoltage();
    }
    
}
