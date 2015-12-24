/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;


import edu.wpi.first.wpilibj.Accelerometer;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.smartdashboard.SendableGyro;
import edu.wpi.first.wpilibj.templates.DigitalAnalogInput;
import edu.wpi.first.wpilibj.templates.RobotMap;

/**
 *
 * @author Halloran Film Studio
 */
public class Sensors{
    
    private final double distancePerEncoderClick = 0.001;   //SET ME!!!!!!!!!!
    private final double accelerometerXZeroVoltage = 1.49;
    private final double accelerometerXSensitivity = 0.3;
    private final double accelerometerYZeroVoltage = 1.51;
    private final double accelerometerYSensitivity = 0.3;
    
    private final int cameraCompression = 50;
    
    private SendableGyro yaw, pitch;
    
    private AnalogChannel ultrasonic;
    
    private DigitalAnalogInput ballSensor1,
                               ballSensor2,
                               ballSensor3;
    
    private Accelerometer accelerometerX,
                          accelerometerY,
                          accelerometerZ;
    
//    private AnalogChannel analogElevatorSensor;
    
    private Encoder[] motorEncoders; //Encoders around the robot are numbered how pins around a chip are, but the first encoder is 0 
    
    //I moved these to the elevator subsystem class. -Sean
//    private Encoder elevatorEncoder;
//    private DigitalInput firstStageUpperLimitSwitch,
//                         firstStageLowerLimitSwitch,
//                         secondStageUpperLimitSwitch,
//                         secondStageLowerLimitSwitch;
    
    //private Counter elevatorCounter;
    
    public Sensors(){
        
        /// gyroscopes
        yaw = new SendableGyro(RobotMap.Ports.Analog.yawGyroPort);
        pitch = new SendableGyro(RobotMap.Ports.Analog.pitchGyroPort);
        
        /// ultrasonic
        ultrasonic = new AnalogChannel(RobotMap.Ports.Analog.ultrasonicSlot);
        
        /// accelerometers
        accelerometerX = new Accelerometer(RobotMap.Ports.Analog.accelerometerXSlot);
        accelerometerY = new Accelerometer(RobotMap.Ports.Analog.accelerometerYSlot);
        accelerometerZ = new Accelerometer(RobotMap.Ports.Analog.accelerometerZSlot);
        accelerometerX.setSensitivity(accelerometerXSensitivity);
        accelerometerX.setZero(accelerometerXZeroVoltage);
        accelerometerY.setSensitivity(accelerometerYSensitivity);
        accelerometerY.setZero(accelerometerYZeroVoltage);
        
        /// ball sensors
        ballSensor1 = new DigitalAnalogInput(RobotMap.Ports.Analog.ballSensor1Port);
        ballSensor2 = new DigitalAnalogInput(RobotMap.Ports.Analog.ballSensor2Port);
        ballSensor3 = new DigitalAnalogInput(RobotMap.Ports.Analog.ballSensor3Port);
        
        /// elevator sensors
        
        
        /// elevator
//        analogElevatorSensor = new AnalogChannel(RobotMap.Ports.Analog.analogElevatorSensorPort);
//        elevatorEncoder = new Encoder(RobotMap.Ports.Digital.encoderElevator[0],RobotMap.Ports.Digital.encoderElevator[1], false, CounterBase.EncodingType.k2X);
//        elevatorEncoder.start();
//        elevatorEncoder.setDistancePerPulse(heightPerEncoderClick);
//        firstStageUpperLimitSwitch = new DigitalInput(RobotMap.Ports.Digital.firstStageUpperLimitSwitchPort);
//        firstStageLowerLimitSwitch = new DigitalInput(RobotMap.Ports.Digital.firstStageLowerLimitSwitchPort);
//        secondStageUpperLimitSwitch = new DigitalInput(RobotMap.Ports.Digital.secondStageUpperLimitSwitchPort);
//        secondStageLowerLimitSwitch = new DigitalInput(RobotMap.Ports.Digital.secondStageLowerLimitSwitchPort);
        
        /// encoders
        motorEncoders = new Encoder[4];
        motorEncoders[0] = new Encoder(RobotMap.Ports.Digital.encoderFrontLeft[0], RobotMap.Ports.Digital.encoderFrontLeft[1], false, CounterBase.EncodingType.k2X);
        motorEncoders[1] = new Encoder(RobotMap.Ports.Digital.encoderRearLeft[0], RobotMap.Ports.Digital.encoderRearLeft[1], false, CounterBase.EncodingType.k2X);
        motorEncoders[2] = new Encoder(RobotMap.Ports.Digital.encoderFrontRight[0], RobotMap.Ports.Digital.encoderFrontRight[1], true, CounterBase.EncodingType.k2X);
        motorEncoders[3] = new Encoder(RobotMap.Ports.Digital.encoderRearRight[0], RobotMap.Ports.Digital.encoderRearRight[1], true, CounterBase.EncodingType.k2X);
        for(int i =0; i<motorEncoders.length; i++){
            motorEncoders[i].start();
            motorEncoders[i].setDistancePerPulse(distancePerEncoderClick);
        }
    }
    
    public SendableGyro getYawGyro(){
        return yaw;
    }
    
    public SendableGyro getPitchGyro(){
        return pitch;
    }
    
    public double getPitch(){
        return pitch.getAngle();
    }
    
    public double getYaw(){
        return yaw.getAngle();
    }
    
    public double getXAcceleration(){
        return accelerometerX.getAcceleration();
    }
    
    
    public double getYAcceleration(){
        return accelerometerY.getAcceleration();
    }
    
    public double getZAcceleration(){
        return accelerometerZ.getAcceleration();
    }
    
    public double getUltrasonicDistance(){
        return ultrasonic.getAverageVoltage()/(5.0/512.0);
    }
    
    public boolean rawBallSpace1Filled(){
        return !ballSensor1.get();
    }
    
    public boolean rawBallSpace2Filled(){
        return !ballSensor2.get();
    }
    
    public boolean rawBallSpace3Filled(){
        return !ballSensor3.get();
    }

    public boolean ballSpace1Filled(){
        return rawBallSpace1Filled();
    }
    
    public boolean ballSpace2Filled(){
        return rawBallSpace2Filled() && ballSpace1Filled();
    }
    
    public boolean ballSpace3Filled(){
        return rawBallSpace3Filled() && ballSpace2Filled();
    }
    
    public int numBallsInChamber(){
        int numBalls=0;
        
        if(this.ballSpace1Filled())
            numBalls++;
        if(this.ballSpace2Filled())
            numBalls++;
        if(this.ballSpace3Filled())
            numBalls++;
        
        return numBalls;
    }
    
    public boolean chamberFilled(){
        return (ballSpace1Filled() && ballSpace2Filled() && ballSpace3Filled());
    }
    
    public boolean chamberEmpty(){
        return !(ballSpace1Filled() || ballSpace2Filled() || ballSpace2Filled());
    }
    
//    public double getElevatorSensorVoltage(){
//        return analogElevatorSensor.getAverageVoltage();
//    }
    
//    public boolean elevatorInScorePosition(){
//        return (getElevatorSensorVoltage() > this.elevatorSensorPeakVoltage);
//    }
    
    public void resetDistance(){
        for(int i =0; i<motorEncoders.length; i++)
            motorEncoders[i].reset();
    }
    public double getAverageEncoderDistance(){
        return (motorEncoders[0].getDistance()+motorEncoders[1].getDistance()+motorEncoders[2].getDistance()+motorEncoders[3].getDistance())/4;
    }
    
    public int getFrontLeftEncoderValue()
    {
        return motorEncoders[0].getRaw();
    }
    
    public int getRearLeftEncoderValue()
    {
        return motorEncoders[1].getRaw();
    }
    
    public int getFrontRightEncoderValue()
    {
        return motorEncoders[2].getRaw();
    }
    
    public int getRearRightEncoderValue()
    {
        return motorEncoders[3].getRaw();
    }
}
