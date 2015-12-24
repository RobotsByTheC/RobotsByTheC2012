package edu.wpi.first.wpilibj;

public class AutonomousFunctions {

    private ADXL345_I2C    accelerometer;
    private Gyro           gyro;
    private BestRobotDrive robotDrive;
    
    /* PID stuff */
    private PIDController PIDControl;
    private double errorX   = 0,
                   errorY   = 0,
                   errorZ   = 0,
                   defaultX = 0,
                   defaultY = 0,
                   defaultZ = -1;
    private double k_P = 0.2,
                   k_I = 0.0,
                   k_D = 0.0,
            robotspeed = 0.0;
    
    private final int accelSlot = 1; //Slot the ADXL345_I2C is plugged into on the digital sidecar
    private final int gyroSlot  = 1; //Slot the gyro is plugged into on the Analog breakout
    
    /* Enables balancing */
    private boolean enabled = false;
    
    public AutonomousFunctions(BestRobotDrive drive){
        accelerometer = new ADXL345_I2C(accelSlot, ADXL345_I2C.DataFormat_Range.k2G);
        gyro = new Gyro(gyroSlot);
        robotDrive = drive;
        //PIDControl = new PIDController(k_P, k_I, k_D, accelerometerstuffthingohgodwat, robotSpeed);  //PIDController can't seem to use the ADXL345_I2C (or any axes) as a PIDSource
        //PIDControl.setTolerance(1); /* One percent tolerance for error (approx. 3.6 degrees on bridge) */
    }
    
    /**
     * Gets the default readings of the accelerometer at the beginning of the match.
     * Call during an init() period.
     * These values should be about 0, 0, -1, respectively.
     */
    public void setAccelerationDefaults(){
        defaultX = accelerometer.getAcceleration(ADXL345_I2C.Axes.kX);
        defaultY = accelerometer.getAcceleration(ADXL345_I2C.Axes.kY);
        defaultZ = -Math.abs(accelerometer.getAcceleration(ADXL345_I2C.Axes.kZ));
    }
    
    /**
     * Enables the balancing method.
     */
    public void enableBalancing(){
        enabled = true;
    }
    
    /**
     * Disables the balancing method.
     */
    public void disableBalancing(){
        enabled = false;
    }
    
    /**
     * Checks to see if balancing is enabled 
     */
    public boolean isBalancingEnabled(){
        return enabled;
    }
    
    /**
     * Automatically balances on a bridge.
     * An easy way to test this method is having a slider or similar on the SmartDashboard, so we won't need to reboot the robot a lot.
     * @param m_long   True if going up long way, else false.
     * @param speedMod Modifier for the speed - higher numbers result in lower speeds. 
     * @param turnMod  Modifier for turning while driving - higher number is higher speed. 
     * X is short axis of robot, Y is long axis. If we crabwalk onto the ramp, swap X and Y in this method.
     * Note: Set this up to use PIDController
     */
    public void balance(boolean m_long, double speedMod, double turnMod){
        double x = 0,
               y = 0;
        double motorSpeed = 0;
        if(m_long == true){ //If is going up long way
            while(enabled){
                x = accelerometer.getAcceleration(ADXL345_I2C.Axes.kX);
                y = accelerometer.getAcceleration(ADXL345_I2C.Axes.kY);
                motorSpeed = Math.sqrt((Math.abs(defaultZ + Math.abs(accelerometer.getAcceleration(ADXL345_I2C.Axes.kZ))) / speedMod));
                if(y < defaultY){     //If front is higher than back, goes forward
                    if(x == defaultX) //Going on perfectly
                        robotDrive.jaguarDrive(motorSpeed, motorSpeed, motorSpeed, motorSpeed);
                    if(x < defaultX)  //Angled to the left on the bridge
                        robotDrive.jaguarDrive(motorSpeed * turnMod, motorSpeed, motorSpeed * turnMod, motorSpeed);
                    if(x > defaultX)  //Angled to the right on the bridge
                        robotDrive.jaguarDrive(motorSpeed, motorSpeed * turnMod, motorSpeed, motorSpeed * turnMod);
                }
                if(y > defaultY){    //If front is lower than back, goes backward
                    if(x == defaultX)
                        robotDrive.jaguarDrive(-motorSpeed, -motorSpeed, -motorSpeed, -motorSpeed);
                    if(x < defaultX)
                        robotDrive.jaguarDrive(-motorSpeed * turnMod, -motorSpeed, -motorSpeed * turnMod, -motorSpeed);
                    if(x > defaultX)
                        robotDrive.jaguarDrive(-motorSpeed, -motorSpeed * turnMod, -motorSpeed, -motorSpeed * turnMod);
                }
                if(y == defaultY)   //If bridge is horizontal
                    robotDrive.jaguarDrive(0, 0, 0, 0);
            }
        }
        else{ //if is going up short way
            while(enabled){
                x = accelerometer.getAcceleration(ADXL345_I2C.Axes.kX);
                y = accelerometer.getAcceleration(ADXL345_I2C.Axes.kY);
                motorSpeed = Math.sqrt((Math.abs(defaultZ - accelerometer.getAcceleration(ADXL345_I2C.Axes.kZ)) / speedMod));
                if(x < defaultX){     //If front is higher than back, goes forward
                    if(y == defaultY) //Going on perfectly
                        robotDrive.jaguarDrive(motorSpeed, motorSpeed, motorSpeed, motorSpeed);
                    if(y < defaultY)  //Angled to the left on the bridge
                        robotDrive.jaguarDrive(motorSpeed * turnMod, motorSpeed, motorSpeed * turnMod, motorSpeed);
                    if(y > defaultY)  //Angled to the right on the bridge
                        robotDrive.jaguarDrive(motorSpeed, motorSpeed * turnMod, motorSpeed, motorSpeed * turnMod);
                }
                if(x > defaultX){    //If front is lower than back, goes backward
                    if(y == defaultY)
                        robotDrive.jaguarDrive(-motorSpeed, -motorSpeed, -motorSpeed, -motorSpeed);
                    if(y < defaultY)
                        robotDrive.jaguarDrive(-motorSpeed * turnMod, -motorSpeed, -motorSpeed * turnMod, -motorSpeed);
                    if(y > defaultY)
                        robotDrive.jaguarDrive(-motorSpeed, -motorSpeed * turnMod, -motorSpeed, -motorSpeed * turnMod);
                }
                if(x == defaultX)   //If bridge is horizontal
                    robotDrive.jaguarDrive(0, 0, 0, 0);
            }
        }
    }
    
    public double getDefaultX(){
            return defaultX;
    }
    
    public double getDefaultY(){
        return defaultY;
    }
    
    public double getDefaultZ(){
        return defaultZ;
    }
    
    public ADXL345_I2C getAccelerometer(){
        return accelerometer;
    }
    
    public Gyro getGyro(){
        return gyro;
    }
}
