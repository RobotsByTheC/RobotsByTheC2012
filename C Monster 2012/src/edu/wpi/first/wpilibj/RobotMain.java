package edu.wpi.first.wpilibj;

import edu.wpi.first.wpilibj.camera.*;

public class RobotMain extends IterativeRobot{    
    
    /*/~~Beginning of declarations~~/*/
    
    /*Used objects*/
    AutonomousFunctions autonomous;
    BestRobotDrive      robotDrive;
    Elevator            elevator;
    Sweeper             sweeper;
    
    /*Sensors*/
    AxisCamera  camera;
    Gyro        gyro;
    ADXL345_I2C accelerometer;
    Encoder     liftEncoder;
    
    /*Driver inputs*/
    Joystick  driveStick; //Controls all driving and bridge manipulation
    final int MecanumButton   = 3, /*Top button, uses Chris' mecanum magic*/
              CrabLeftButton  = 4,
              CrabRightButton = 5,
              Trigger         = 0;
    
    Joystick elevatorStick; //Controls all ball manipulation and the elevator
    final int reverseSweeper  = 0, //Placeholder
              stopSweeper     = 0, //Placeholder
              holdElevator    = 0, //Placeholder
              dumpButton      = 0; //Trigger; actual value
    
    /*Jaguar slots*/
    final int FL = 1,
              FR = 2,
              RL = 3,
              RR = 4,
              elevatorSlot = 5,
              sweeperSlot  = 6;
    
    /*Crabwalking speed to direct left or direct right*/
    double LRSpeed = 0.75;
    
    /*/~~End of declarations~~/*/
    
    public void robotInit(){
        liftEncoder = new Encoder(1, 2);
        
        robotDrive = new BestRobotDrive(FL, FR, RL, RR);
        elevator   = new Elevator(elevatorSlot, liftEncoder);
        sweeper    = new Sweeper(sweeperSlot);
        autonomous = new AutonomousFunctions(robotDrive);
        
        accelerometer = autonomous.getAccelerometer();
        gyro          = autonomous.getGyro();
        
        driveStick    = new Joystick(1);
        elevatorStick = new Joystick(2);
        
        camera = AxisCamera.getInstance();
        camera.writeResolution(AxisCamera.ResolutionT.k320x240);
        camera.writeCompression(30);
        camera.writeBrightness(0);
        
        SmartDashboard.init();
    }
    
    public void autonomousInit(){
    }
    
    public void autonomousPeriodic(){
    
    }
    
    public void teleopInit(){
        autonomous.setAccelerationDefaults();
        SmartDashboard.init();
    }
    
    public void teleopPeriodic(){
        SmartDashboard.log(accelerometer.getAcceleration(ADXL345_I2C.Axes.kX), "X acceleration: ");
        SmartDashboard.log(accelerometer.getAcceleration(ADXL345_I2C.Axes.kY), "Y acceleration: ");
        SmartDashboard.log(accelerometer.getAcceleration(ADXL345_I2C.Axes.kZ), "Z acceleration: ");

        SmartDashboard.log(elevator.getHeight(), "Elevator height: ");
        
        /*/Sweeper stuff/*/
        if(elevator.isOutOfCollectionRange()) //Takes priority over driver control
            sweeper.stop();
        else if(elevatorStick.getRawButton(stopSweeper) && !elevatorStick.getRawButton(reverseSweeper))
            sweeper.stop();
        else if(elevatorStick.getRawButton(reverseSweeper))
            sweeper.reject();
        else 
            sweeper.sweep(); //Only sweeps balls in if no other buttons are pressed and the elevator is at the correct height.
        
        
        /*/Elevator stuff/*/
        if(elevator.getHeight() > elevator.getMinHeight()) //If below min height, go to min height
            elevator.goToHeight(elevator.getMinHeight());
        else if(elevator.getHeight() < elevator.getMaxHeight()) //Else if above max height, go to max height
            elevator.goToHeight(elevator.getMaxHeight());
        else if(elevatorStick.getX() == 0 || elevatorStick.getRawButton(holdElevator)) //Else stay at current height
            elevator.stayAtHeight();
        else if(elevatorStick.getX() != 0) //Else move with the joystick
            elevator.move(elevatorStick.getX());
        
        if(elevatorStick.getRawButton(dumpButton))
            elevator.dump();        
        
        /*/Driving stuff/*/
        if(!driveStick.getRawButton(MecanumButton) && !driveStick.getRawButton(CrabLeftButton) && !driveStick.getRawButton(CrabRightButton))
            robotDrive.arcadeDrive(driveStick);
        else if(driveStick.getRawButton(MecanumButton) && !driveStick.getRawButton(CrabLeftButton) && !driveStick.getRawButton(CrabRightButton))
            robotDrive.proveThatMecanumsAreAwesome(driveStick);
        else if(driveStick.getRawButton(CrabLeftButton))
            robotDrive.crabLeft(LRSpeed);
        else if(driveStick.getRawButton(CrabRightButton))
            robotDrive.crabRight(LRSpeed);            
    }
}
