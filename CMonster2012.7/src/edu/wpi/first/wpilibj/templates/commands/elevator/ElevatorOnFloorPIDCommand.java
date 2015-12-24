/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands.elevator;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.templates.commands.CommandBase;

/**
 *
 * @author Halloran Film Studio
 */
public class ElevatorOnFloorPIDCommand extends PIDCommand{

    private static final double kP = 5,
                                kI = 0,
                                kD = 0;
    private final double kElavatorOnFloor = 0;
    
    public ElevatorOnFloorPIDCommand(){
        super(kP, kI, kD);
        this.requires(CommandBase.elevatorSubsystem);
    }
    
    protected void initialize(){
        //setSetpoint(kElavatorOnFloor);
    }

    protected void execute(){
        //CommandBase.elevatorSubsystem.setMotorSpeed(kP);
    }

    protected boolean isFinished(){
        //return CommandBase.elevatorSubsystem.atFullHeight();
        return true;
    }

    protected void end(){
        CommandBase.elevatorSubsystem.setMotorSpeed(0);
    }

    protected void interrupted() {
    }
    
    protected double returnPIDInput(){
        return CommandBase.elevatorSubsystem.getElevatorHeight();
    }

    protected void usePIDOutput(double output){
    }
    
}
