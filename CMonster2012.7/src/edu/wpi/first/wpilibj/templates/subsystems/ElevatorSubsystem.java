/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.DigitalAnalogInput;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.commands.elevator.ConstantElevatorControl;

/**
 *
 * @author Sean Halloran
 */
public class ElevatorSubsystem extends Subsystem {

    private double elevatorHeightThreshold = 5; //SET ME!!!!!!!!!!!
    private Jaguar jaguar;
    private Encoder encoder;
    private DigitalAnalogInput elevatorSensor1, elevatorSensor2, elevatorSensor3;
    private final double heightPerEncoderClick = 1;      //SET ME!!!!!!!!!!
    private double maxHeight = 60, //SET ME!!!!!!!!!!!!!!!
                   minHeight = .5; //The bottom of mailbox is 1/2" above the floor.
    //private final double elevatorLength = 4;
    private boolean heightCalibrated;
    private boolean putInStoredPosition;

    public ElevatorSubsystem() {
        jaguar = new Jaguar(RobotMap.Ports.PWM.jaguarElevator);
        encoder = new Encoder(RobotMap.Ports.Digital.encoderElevator[0], RobotMap.Ports.Digital.encoderElevator[1], false, CounterBase.EncodingType.k4X);
//        elevatorSensor1 = new DigitalAnalogInput(RobotMap.Ports.Analog.elevatorSensor1Port);
//        elevatorSensor2 = new DigitalAnalogInput(RobotMap.Ports.Analog.elevatorSensor2Port);
//        elevatorSensor3 = new DigitalAnalogInput(RobotMap.Ports.Analog.elevatorSensor3Port);
        encoder.start();
        encoder.setDistancePerPulse(heightPerEncoderClick);
        heightCalibrated = false;
        putInStoredPosition = false;
    }

    protected void initDefaultCommand() {
        this.setDefaultCommand(new ConstantElevatorControl());
    }
    
    public Encoder getEncoder(){
        return encoder;
    }

    public boolean atGround() {
        //return Math.abs(getElevatorHeight()-minHeight)<5;
        boolean atGround = false;
        
        if(elevatorSensor1.get() == false && elevatorSensor2.get() == false && elevatorSensor3.get() == true)
            atGround = true;
        
        return atGround;
    }

//    public boolean atFullHeight() {
//        return (firstStageUpperLimitSwitch.get() && secondStageUpperLimitSwitch.get());
//    }

    public double getElevatorHeight() {
        //return encoder.getDistance() + minHeight;
        return maxHeight + encoder.getDistance();
    }

    public void setMotorSpeed(double x) {
        jaguar.set(x);
    }

    public int getEncoderValue() {
        return encoder.get();
    }

    public boolean extendedBeyondFrame() {
        return (getElevatorHeight() > elevatorHeightThreshold);
    }

    public void setMaxHeight() {
        //maxHeight = encoder.getDistance();
        //minHeight = maxHeight - elevatorLength;
        encoder.reset();
        heightCalibrated = true;
    }
    
    public boolean isHeightCalibrated(){
        return heightCalibrated;
    }

    public boolean isPutInStoredPosition() {
        return putInStoredPosition;
    }

    public void setPutInStoredPosition(boolean putInStoredPosition) {
        this.putInStoredPosition = putInStoredPosition;
    }
    
    
}

/*
 * Measure:
 *  max and min height
 *  distance per encoder click
 *  stored height of the elevator
 */
