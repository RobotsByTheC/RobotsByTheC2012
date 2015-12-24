/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.DigitalAnalogInput;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.commands.ramp.RampControllerDoNothingCommand;

/**
 *
 * @author
 * team 2084
 */


public class RampControllerSubsystem extends Subsystem{
    
    private Victor victor;
    private Relay spike;
    private boolean unfolded;
    private DigitalAnalogInput extendedLimitSwitch, storedLimitSwitch;
    private final double rampLowerSpeed = .22, rampLowerHighSpeed = .5, rampRaiseSpeed = 0.4, rampHoldDownSpeed = 1;
    
    public RampControllerSubsystem(){
        victor = new Victor(RobotMap.Ports.PWM.victorRampLowerer);
        extendedLimitSwitch = new DigitalAnalogInput(RobotMap.Ports.Analog.rampControllerExtendedLimitSwitchButton);
        storedLimitSwitch = new DigitalAnalogInput(RobotMap.Ports.Analog.rampControllerStoredLimitSwitch);
        unfolded = false;
        spike = new Relay(1);
    }
    
    protected void initDefaultCommand(){
        setDefaultCommand(new RampControllerDoNothingCommand());
    }
    
    public void spin(double speed){
        victor.set(speed);
    }
    
    public boolean isDown(){
        return extendedLimitSwitch.get();
    }
    public boolean isUp(){
        return storedLimitSwitch.get();
    }
    
    public void raise(){
        if(!isUp())
            spin(rampRaiseSpeed);
        else
            stop();
    }
    
    public void lower(){
        if(isUp())
            spin(-rampLowerHighSpeed);
        else if(!isDown())
            spin(-rampLowerSpeed);
        else
            stop();
    }
    
    public void holdDown(){
        if(!isDown()){
            spin(-rampHoldDownSpeed);
            //SmartDashboard.putString("HOLD DOWN ARM", "I'm giv'n 'er all she's got cap'n!");
        }
        else{
            stop();
            //SmartDashboard.putString("HOLD DOWN ARM", "WE ARE DOOOOOWN!!!!");
        }
    }
    
    public void stop(){
        spin(0);
    }
    
    public boolean extendedBeyondFrame(){
        return !isUp();
    }
//    public double getLowerLimitSwitchVoltage(){
//        return extendedLimitSwitch.getVoltage();
//    }
    
    public void setUnfolded(boolean x){
        unfolded = x;
    }
    
    public boolean getUnfolded(){
        return unfolded;
    }
    
    public void toggleUnfolded(){
        unfolded=!unfolded;
    }
    public Victor getVictor(){
        return victor;
    }
    
    public void extendSolenoid(){
        spike.set(Relay.Value.kOn);
    }
    
    public void retractSolenoid(){
        spike.set(Relay.Value.kOff);
    }
   
}
