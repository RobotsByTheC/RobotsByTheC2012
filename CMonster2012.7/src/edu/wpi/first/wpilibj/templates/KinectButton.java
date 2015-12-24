/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.KinectStick;
import edu.wpi.first.wpilibj.buttons.Button;

/**
 *
 * @author
 * team 2084
 */
public class KinectButton extends Button{
    
    private int button;
    private KinectStick kinectStick;
    
    public KinectButton(KinectStick stick, int button){
        kinectStick = stick;
        this.button = button;
    }

    public boolean get() {
        return kinectStick.getRawButton(button);
    }
    
}
