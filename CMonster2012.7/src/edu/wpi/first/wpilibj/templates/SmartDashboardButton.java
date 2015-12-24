/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.networktables.NetworkTableKeyNotDefined;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Sean Halloran
 */
public class SmartDashboardButton extends Button{
    private String keyLocal;
    public SmartDashboardButton(String key) {
        keyLocal=key;
        SmartDashboard.putBoolean(keyLocal, false);
    }

    
    
    public boolean get() {
        try {
            if(SmartDashboard.getBoolean(keyLocal)){
                SmartDashboard.putBoolean(keyLocal, false);
                return true;
            }
            else{
                return false;
            }
        } catch (NetworkTableKeyNotDefined ex) {
            return false;
        }
    }
    
}
