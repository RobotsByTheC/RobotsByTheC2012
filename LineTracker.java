/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;

/**
 *
 * @author Sean Halloran
 */
public class LineTracker {
    private DigitalInput sensor;
    private boolean signal, lastSignal, movingUp;
    double barHeight, height;
    
    public LineTracker(int port, double barHeight){
        sensor = new DigitalInput(port);
        this.barHeight=barHeight;
        movingUp=false;
        height = 0;
    }
    
    public void update(){
        lastSignal = signal;
        signal = sensor.get();
        
        if(signal!=lastSignal){
            if(movingUp)
                height+=barHeight;
            else
                height-=barHeight;
        }
        
    }
    
    public void setMovingUp(boolean x){
        movingUp = x;
    }
    
    public double getHeight(){
        return height;
    }
    
    public void resetHeight(){
        height=0;
    }
}
