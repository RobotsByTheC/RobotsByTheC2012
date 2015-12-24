/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.subsystems.Sensors;

/**
 *
 * @author Sean Halloran
 */
public class SensorDisplayCommand extends CommandBase{

    private Sensors sensors;
    
    public SensorDisplayCommand(){
        this.setRunWhenDisabled(true);
        sensors = CommandBase.sensors;
    }
      
    protected void initialize() {
        
    }

    protected void execute() {
                
        /// ultrasonic display
        //SmartDashboard.putDouble("Ultrasonic Distance", sensors.getUltrasonicDistance());
        
        /// gyro display
        //SmartDashboard.putDouble("Yaw Gyro", sensors.getYawGyro().getAngle());
        SmartDashboard.putDouble("Pitch Gyro", sensors.getPitchGyro().getAngle());
        //Try this sometime
//        SmartDashboard.putData("Yaw Gyro", sensors.getYawGyro());
//        SmartDashboard.putData("Pitch Gyro", sensors.getPitchGyro());
//        
        //SmartDashboard.putDouble("Elevator Encoder Rate", elevatorSubsystem.getEncoder().getRate());
        
        /// accelerometer display
        SmartDashboard.putDouble("X acceleration", sensors.getXAcceleration());
        SmartDashboard.putDouble("Y acceleration", sensors.getYAcceleration());
        SmartDashboard.putDouble("Z acceleration", sensors.getZAcceleration());
        
//          And this (cont from line 36)
//          if(sensors.getYawGyro().getUpdatePeriod()!=SmartDashboard.getInt("update", 10))
//           sensors.getYawGyro().setUpdatePeriod(SmartDashboard.getInt("update", 10));
         
        /// encoders
//        SmartDashboard.putInt("Front left encoder", sensors.getFrontLeftEncoderValue());
//        SmartDashboard.putInt("Rear left encoder", sensors.getRearLeftEncoderValue());
//        SmartDashboard.putInt("Front right encoder", sensors.getFrontRightEncoderValue());
//        SmartDashboard.putInt("Rear right encoder", sensors.getRearRightEncoderValue());
//        
//        SmartDashboard.putInt("ElevatorEncoder", elevatorSubsystem.getEncoderValue());
//        
        SmartDashboard.putString("OPERATOR_OVERRIDE", (oi.overrideEnabled())?"Enabled":"Disabled");
        
        /// servo display
        //SmartDashboard.putDouble("Elevator servo value", dumperSubsystem.getDoorServoPosition());
        //SmartDashboard.putDouble("Ramp Servo 1", dumperSubsystem.getRampServo1Position());
        //SmartDashboard.putDouble("Ramp Servo 2", dumperSubsystem.getRampServo2Position());
        /// is it balanced?
        double gForce = sensors.getZAcceleration();
        
        SmartDashboard.putBoolean("Balanced", (gForce <= 0.05 && gForce >= -0.05));
        
        SmartDashboard.putBoolean("Ramp Lower Limit Switch", rampControllerSubsystem.isDown());
        SmartDashboard.putBoolean("Ramp Upper Limit Switch", rampControllerSubsystem.isUp());
        //SmartDashboard.putDouble("Ramp Lower Limit Switch Voltage", rampControllerSubsystem.getLowerLimitSwitchVoltage());
        
        SmartDashboard.putBoolean("Ball Sensor 1", sensors.rawBallSpace1Filled());
        SmartDashboard.putBoolean("Ball Sensor 2", sensors.rawBallSpace2Filled());
        SmartDashboard.putBoolean("Ball Sensor 3", sensors.rawBallSpace3Filled());
        
        SmartDashboard.putDouble("Back Door Servo", dumperSubsystem.getRearDoorServo().get());
        //SmartDashboard.putBoolean("Upper Ramp Limit Switch", rampControllerSubsystem.isUp());
        //SmartDashboard.putDouble("RampSpeed", rampControllerSubsystem.getVictor().get());
        
//        SmartDashboard.putDouble("Front Right Wheel Speed", driveBaseSubsystem.getBestRobotDrive().getFrontRightWheelSpeed());
//        SmartDashboard.putDouble("Front Left Wheel Speed", driveBaseSubsystem.getBestRobotDrive().getFrontLeftWheelSpeed());
//        SmartDashboard.putDouble("Rear Right Wheel Speed", driveBaseSubsystem.getBestRobotDrive().getRearRightWheelSpeed());
//        SmartDashboard.putDouble("Rear Left Wheel Speed", driveBaseSubsystem.getBestRobotDrive().getRearLeftWheelSpeed());
//        
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted(){
    }
    
}
   