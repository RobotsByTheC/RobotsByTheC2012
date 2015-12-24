/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.commands.dumper.DumperMonitorCommand;
//import edu.wpi.first.wpilibj.templates.commands.ToggleElevatorDoorCommand;

/**
 *
 * @author
 * team 2084
 */
public class DumperSubsystem extends Subsystem
{
    private Servo doorServo, rampServo, rearDoorServo;
    //private double doorServoSetpoint, rampServoSetpoint;
    
    public static final double elevatorDoorOpenedPosition = 1.0;
    public static final double elevatorDoorClosedPosition = 0.58;
    public static final double rampLiftDownPositionServo1 = 0.94;
   // public static final double rampLiftDownPositionServo2 = 0.0;
    public static final double rampLiftUpPosition1 = 0.6;
    
    public static final double rearDumperDoorClosedPosition = 1.0, rearDumperDoorOpenPosition = 0.628;
    //public static final double rampLiftUpPosition2 = 0.4;
    
    private final double servoPositionTollerance = .1;
    
    
    public DumperSubsystem()
    {
        // make the servo
        doorServo  = new Servo(RobotMap.Ports.PWM.servoElevatorDoor);
        rampServo = new Servo(RobotMap.Ports.PWM.servoDumperRamp1);
        rearDoorServo = new Servo(RobotMap.Ports.PWM.servoRearElevatorDoor);
        //rampServo2 = new Servo(RobotMap.Ports.PWM.servoDumperRamp2);
        
        // tell this class (and ToggleElevatorDoorCommand) what position the servo is being told to go to
        //doorServoSetpoint = elevatorDoorClosedPosition;
        
        // set the servo to be closed
        doorServo.set(elevatorDoorClosedPosition);
        rearDoorServo.set(rearDumperDoorClosedPosition);
    }
    
    protected void initDefaultCommand()
    {
        setDefaultCommand(new DumperMonitorCommand());
    }
    
    /**
     * This will set the servo's position
     * @param pos The servo's position from 0.0 to 1.0
     */
    public void setDoorServoPosition(double pos)
    {
        doorServo.setPosition(pos);
        //setDoorServoSetpoint(pos);
    }
    
    /**
     * This will return the servo's position
     * @return The servo's position
     */
    public double getDoorServoPosition()
    {
        return doorServo.getPosition();
    }
    
    /**
     * This will set the value of where the servo is supposed to be going (for record-keeping)
     * @param point The servo's position from 0.0 to 1.0
     */
//    public void setDoorServoSetpoint(double point)
//    {
//        doorServoSetpoint = point;
//        //setDoorServoPosition(doorServoSetpoint);
//    }
//    
    /**
     * This will get the value of where the servo is supposed to be going (for record-keeping)
     * @return The servo's position from 0.0 to 1.0
     */
//    public double getDoorServoSetpoint()
//    {
//        return doorServoSetpoint;
//    }
        /**
     * This will set the servo's position
     * @param pos The servo's position from 0.0 to 1.0
     */
    public void setRampServo1Position(double pos)
    {
        rampServo.setPosition(pos);
        //setRampServoSetpoint(pos);
    }
    
//    public void setRampServo2Position(double pos)
//    {
//        rampServo2.setPosition(pos);
//        //setRampServoSetpoint(pos);
//    }
    
    /**
     * This will return the servo's position according to the PWM output
     * @return The servo's position according to the PWM output
     */
    public double getRampServo1Position()
    {
        return rampServo.getPosition();
    }
    
//    public double getRampServo2Position(){
//        return rampServo2.getPosition();
//    }
    
    /**
     * This will set the value of where the servo is supposed to be going (for record-keeping)
     * @param point The servo's position from 0.0 to 1.0
     */
//    public void setRampServoSetpoint(double point)
//    {
//        rampServoSetpoint = point;
//    }
    
    /**
     * This will get the value of where the servo is supposed to be going (for record-keeping)
     * @return The servo's position from 0.0 to 1.0
     */
//    public double getRampServoSetpoint()
//    {
//        return rampServoSetpoint;
//    }
    
//    private void updateServoPosition(){
//        setDoorServoPosition(doorServoSetpoint);
//        setRampServo1Position(rampServoSetpoint);
//    }
    
//    public void setRamp(double setPoint){
//        rampServo1.set(setPoint);
//        rampServo2.set(1-setPoint);
//    }
    
    public void raiseRamp(){
        rampServo.set(rampLiftUpPosition1);
        //rampServo2.set(rampLiftUpPosition2);
    }
//    
    public void lowerRamp(){
        rampServo.set(rampLiftDownPositionServo1);
        //rampServo2.set(rampLiftDownPositionServo2);
    }
    
    public void openDoor(){
        doorServo.set(elevatorDoorOpenedPosition);
    }
    
    public void closeDoor(){
        doorServo.set(elevatorDoorClosedPosition);
    }
    
    //All of these methods have no real purpose because you cannot read from a servo, only what is being sent to the servo
    
    public boolean doorOpen(){
        return (Math.abs(elevatorDoorOpenedPosition-doorServo.get())<servoPositionTollerance);
    }
    
    public boolean doorClosed(){
        return (Math.abs(elevatorDoorClosedPosition-doorServo.get())<servoPositionTollerance);
    }
    
    public boolean rampRaised(){
        return (Math.abs(rampLiftUpPosition1-rampServo.get())<servoPositionTollerance);
    }
    
    public boolean rampLowered(){
        return (Math.abs(rampLiftDownPositionServo1-rampServo.get())<servoPositionTollerance);
    }
    
    public void openRearDoor(){
        rearDoorServo.set(rearDumperDoorOpenPosition);
    }
    
    public void closeRearDoor(){
        rearDoorServo.set(rearDumperDoorClosedPosition);
    }

    public Servo getRearDoorServo() {
        return rearDoorServo;
    }
    
}
