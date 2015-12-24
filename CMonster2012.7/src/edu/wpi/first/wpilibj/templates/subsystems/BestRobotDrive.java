/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates.subsystems;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
// jaguarDrive((jX+jY)/2, -(jY-jX)/2, (jY-jX)/2, -(jX+jY)/2);
/**
 *
 * @author Sean Halloran
 */
public class BestRobotDrive extends RobotDrive{

    public static final double quickCrabSpeed = .95;
    
    double precisionDriving;
    
    boolean frontLeftInverted,
            frontRightInverted,
            rearLeftInverted,
            rearRightInverted;
    
    public BestRobotDrive(int FL, int RL, int FR, int RR){
        super(FL, RL, FR, RR);
        frontLeftInverted=false;
        frontRightInverted=true;
        rearLeftInverted=true;
        rearRightInverted=true;
        setInvertedMotor(MotorType.kFrontLeft, frontLeftInverted);
        setInvertedMotor(MotorType.kRearLeft, rearLeftInverted);
        setInvertedMotor(MotorType.kFrontRight, frontRightInverted);
        setInvertedMotor(MotorType.kRearRight, rearRightInverted);
        precisionDriving=1;
    }
    public BestRobotDrive(int leftMotor, int rightMotor){
        super(leftMotor, rightMotor);
        precisionDriving=1;
    }

    public void setPrecisionDriving(double x){ precisionDriving=x;}
    public double getprecisionDriving(){ return precisionDriving;}

    public void arcadeDrive(GenericHID stick, boolean squaredInputs){
        arcadeDrive(stick.getY()*precisionDriving, stick.getX()*precisionDriving, squaredInputs);
    }
    
    public void mecanumDrive(Joystick j)
    {
        mecanumDriveXY(j.getX(), j.getY());
    }
    
    public void mecanumDriveXY(double joystickX, double joystickY){
        double jX = -joystickX*precisionDriving;
        double jY =  joystickY*precisionDriving;
        
        /********** our custom Mecanum drive *******/
        // CHRIS WILLIAMS's MECANUM MAGIC
        jaguarDrive((jX+jY)/2, -(jY-jX)/2, (jY-jX)/2, (jX+jY)/2);
        //          FrontLeft, RearLeft, FrontRight , RearRight
    }
    
    public void mecanumDrivePolar(double magnitude, double angle){
        magnitude/=Math.cos(Math.toRadians((angle+45)%90-45)); //try this, then comment this line out, and try it again
        mecanumDriveXY(magnitude*Math.cos(Math.toRadians(angle)), magnitude*Math.sin(Math.toRadians(angle)));
    }

    public void jaguarDrive(double FL, double RL, double FR, double RR)
    /* there is an interesting problem: this method goes FR, FL, RR, RL,
     * but look at the comment under Chris Williams's mecanum magic and there, it goes FL, FR, RL, RR. That might be a problem.
     */
    {
        m_frontLeftMotor.set(FL);
        m_rearLeftMotor.set(RL);
        m_frontRightMotor.set(FR);
        m_rearRightMotor.set(RR);
        if (m_safetyHelper != null) m_safetyHelper.feed();
    }

    public void crabLeft(double speed)
    {
//        double jX = -speed;
//        double jY = 0;
        //jaguarDrive((jX+jY)/2, -(jY-jX)/2, (jY-jX)/2, -(jX+jY)/2);
        mecanumDriveXY(-speed, 0);
    }

    public void crabRight(double speed)
    {
//        double jX = speed;
//        double jY = 0;
        //jaguarDrive((jX+jY)/2, -(jY-jX)/2, (jY-jX)/2, -(jX+jY)/2);
        mecanumDriveXY(speed, 0);
    }

//    public void rotate(double speed){ //pos numbers go counterclockwise
//        jaguarDrive(speed, speed, speed, speed);
//    }

//    public void Drive(double speed, double curve){
//        this.setInvertedMotor(MotorType.kFrontRight, false);
//        this.setInvertedMotor(MotorType.kRearRight, false);
//        super.drive(speed, curve);
//        this.setInvertedMotor(MotorType.kFrontRight, true);
//        this.setInvertedMotor(MotorType.kRearRight, true);
//    }
    
    public void invertAllMotors(){
        rearRightInverted=!rearRightInverted;
        rearLeftInverted=!rearLeftInverted;
        frontLeftInverted=!frontLeftInverted;
        frontRightInverted=!frontRightInverted;
        setInvertedMotor(MotorType.kFrontLeft, frontLeftInverted);
        setInvertedMotor(MotorType.kRearLeft, rearLeftInverted);
        setInvertedMotor(MotorType.kFrontRight, frontRightInverted);
        setInvertedMotor(MotorType.kRearRight, rearRightInverted);
        
    }
    
    public double getFrontRightWheelSpeed(){
        return this.m_frontRightMotor.get();
    }
    
    public double getFrontLeftWheelSpeed(){
        return this.m_frontLeftMotor.get();
    }
    
    public double getRearRightWheelSpeed(){
        return this.m_rearRightMotor.get();
    }
    
    public double getRearLeftWheelSpeed(){
        return this.m_rearLeftMotor.get();
    }
    
 /*   public void turnToAngle(int goalAngle){ //good for resetting the startAngle in EpicSensors. Example: turnToAngle(360-EpicSensors.startingAngle)
        while (EpicSensors.getAngleDegrees() != goalAngle)
            rotate(.1);
    }*/
}