/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.commands.ArmWatchdog;
import edu.wpi.first.wpilibj.templates.commands.CommandBase;
import edu.wpi.first.wpilibj.templates.commands.SensorDisplayCommand;
import edu.wpi.first.wpilibj.templates.commands.elevator.ElevatorBallMonitorCommand;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotTemplate extends IterativeRobot {

    public static ArmWatchdog armWatchdog;
    //public static Emotions emotions;
    
    Command sensorDisplay;
    Command hybridyCommandGroup;
    Command elevatorBallMonitorCommand;
    
    private double oldTime;
    private boolean thirtySecondWarningToggle;
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        // instantiate the command used for the autonomous period
        // Initialize all subsystems
        CommandBase.init();
        sensorDisplay = new SensorDisplayCommand();
        armWatchdog = new ArmWatchdog();
        
        elevatorBallMonitorCommand = new ElevatorBallMonitorCommand();
        
        sensorDisplay.start();
        armWatchdog.start();
        armWatchdog.setOn(false);
        
        oldTime = 0;
   
    }

    public void autonomousInit() {
        if(true){
            SmartDashboard.putString("Hybrid Mode", "Automatic");
            hybridyCommandGroup = new AutomaticHybridPeriodCommandGroup();
        }
        else{
            SmartDashboard.putString("Hybrid Mode", "Kinect");
            hybridyCommandGroup = new HybridPeriodKinectCommandGroup();
        }
        hybridyCommandGroup.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }
    
    
    public void teleopInit() {
        //CommandBase.elevatorSubsystem.setMotorSpeed(0);
        if(hybridyCommandGroup!=null)
            hybridyCommandGroup.cancel();
        elevatorBallMonitorCommand.start();
        SmartDashboard.putString("Hybrid Mode", "Finished");
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        
//        if(CommandBase.elevatorSubsystem.getCurrentCommand().getName().equals("RaiseElevatorCommand"))
//            emotions.display(emotions.raisingElevator);
//        else
//            emotions.display(emotions.seriousFace);
//        
        if(135.0 - DriverStation.getInstance().getMatchTime() <= 50)
        {
            if(Timer.getFPGATimestamp() - oldTime >= 0.2)
            {
                SmartDashboard.putBoolean("30 SEC LEFT!!", thirtySecondWarningToggle);
                thirtySecondWarningToggle = !thirtySecondWarningToggle;
                oldTime = Timer.getFPGATimestamp();
            }
        }
    }
    
    public void disabledInit(){
        elevatorBallMonitorCommand.cancel();
        
        //emotions.display(emotions.happyFace);
    }

    public void disabledPeriodic() {
        if(CommandBase.oi.getTestStick().getZ()<0){
            SmartDashboard.putString("Hybrid Mode", "Automatic");
            //hybridyCommandGroup = new AutomaticHybridPeriodCommandGroup();
        }
        else{
            SmartDashboard.putString("Hybrid Mode", "Kinect");
            //hybridyCommandGroup = new HybridPeriodKinectCommandGroup();
        }
    }
}

/***************************************************************
 * 
 * TO DO:
 * 

 * map more commands to buttons on smart dashboard
 * DONE(?) label the joystick
 * DONE prevent wheel movement directly after manual elevator control
 * fix mencanum
 * DONE readout for front of the robot
 * design the SmartDashboard interface
 * 
 * 
 * 
 * Values to find:
 * 
 * Max height the elevator can be without extending beyond the frame in ElevatorSubsystem
 * DONE The speed to drive at and g force limit in DriveUntilSmashCommand
 * QuickCrabSpeed in BestRobotDrive
 * height per click in ElevatorSubsystem
 * max and min height of the elevator in ElevatorSubsystem
 * NOPE distance per encoder click in sensors
 * DONE the open and closed setpoints for the servo on the elevator door, in ToggleElevatorDoorCommand
 * stored height of the elevator
 * DONE all the servo setpoints
 * DONE set speed of the ramp controller
 * 
 * 
 * 
 * Commands to code:
 *  rotate
 *
 * 
 * 
 */