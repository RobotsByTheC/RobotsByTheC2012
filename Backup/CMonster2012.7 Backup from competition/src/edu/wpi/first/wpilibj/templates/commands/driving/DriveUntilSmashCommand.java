/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands.driving;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.templates.commands.CommandBase;

/**
 *
 * @author peter
 */
public class DriveUntilSmashCommand extends CommandBase {

    private double gForce = 0.0;
    private double drivingSpeed = 0.75;
    private double currentSpeed = 0.0;
    private final double incrementSpeed = 0.05;
    private final double waitTime = 0.02;
    private final double gForceLimit = 1.5; //SET ME!!!!
    private double time, currentTime;
    private boolean forward;

    public DriveUntilSmashCommand() {
        this(true);
    }

    public DriveUntilSmashCommand(boolean forward) {
        requires(driveBaseSubsystem);
        this.forward = forward;
        if (!forward) {
            drivingSpeed *= -1;
        }
    }

    protected void initialize() {
        time = Timer.getFPGATimestamp();
        //CommandBase.driveBaseSubsystem.getBestRobotDrive().arcadeDrive(drivingSpeed, 0.0);
    }

    protected void execute() {
        //This code causes to accelerate pretty fast, but not immediatly like before
        if (forward) {
            if (currentSpeed < drivingSpeed && Timer.getFPGATimestamp() - currentTime > waitTime) {
                currentSpeed += incrementSpeed;
            }
        } else {
            if (currentSpeed > drivingSpeed && Timer.getFPGATimestamp() - currentTime > waitTime) {
                currentSpeed -= incrementSpeed;
            }
        }
        CommandBase.driveBaseSubsystem.getBestRobotDrive().arcadeDrive(currentSpeed, 0.0);
        //CommandBase.driveBaseSubsystem.getBestRobotDrive().arcadeDrive(drivingSpeed, 0.0);
        gForce = CommandBase.sensors.getXAcceleration();
        currentTime = Timer.getFPGATimestamp();
    }

    protected boolean isFinished() {
        // if the G force is greater than the G force limit, stop the robot
        //return (Math.abs(gForce) >= gForceLimit) && Timer.getFPGATimestamp()-time>=3.0;
        return gForce < -1.28 && timeSinceInitialized()>1;//I moved the timeout to the constructor
    }

    protected void end() {
        CommandBase.driveBaseSubsystem.getBestRobotDrive().drive(0.0, 0.0);
    }

    protected void interrupted() {
        CommandBase.driveBaseSubsystem.getBestRobotDrive().drive(0.0, 0.0);
    }
}
