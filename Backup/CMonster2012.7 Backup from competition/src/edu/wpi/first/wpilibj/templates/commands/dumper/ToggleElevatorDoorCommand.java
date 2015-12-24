///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package edu.wpi.first.wpilibj.templates.commands.dumper;
//
//import edu.wpi.first.wpilibj.templates.commands.CommandBase;
//import edu.wpi.first.wpilibj.templates.subsystems.DumperSubsystem;
//
///**
// *
// * @author
// * team 2084
// */
//public class ToggleElevatorDoorCommand extends CommandBase
//{
//    
//    private double doorTargetPosition = 0;
//    private double rampTargetPosition = 0;
//    
//    protected void initialize()
//    {
//        this.requires(CommandBase.dumperSubsystem);
//        
//        // we have getDoorServoSetpoint() instead of getDoorServoPosition() because the servo might not be at its target position yet...
//        // we want to know where it's _supposed_ to be so you can toggle the door when it's not all the way in position yet (if you'd need)
//        if(CommandBase.dumperSubsystem.getDoorServoPosition() == DumperSubsystem.elevatorDoorOpenedPosition){
//            doorTargetPosition = DumperSubsystem.elevatorDoorClosedPosition;
//            rampTargetPosition = DumperSubsystem.rampLiftDownPositionServo1;
//        }
//        else if(CommandBase.dumperSubsystem.getDoorServoPosition() == DumperSubsystem.elevatorDoorClosedPosition){
//            doorTargetPosition = DumperSubsystem.elevatorDoorOpenedPosition;
//            rampTargetPosition = DumperSubsystem.rampLiftUpPosition1;
//        }
//    }
//
//    protected void execute()
//    {
//        CommandBase.dumperSubsystem.setRampServo1Position(rampTargetPosition); // tell the servo to go there
//        CommandBase.dumperSubsystem.setRampServo2Position(rampTargetPosition); // remember where we told the servo to go (so the toggling works)
//        CommandBase.dumperSubsystem.setDoorServoPosition(doorTargetPosition); // tell the servo to go there
//        //CommandBase.dumperSubsystem.setDoorServoSetpoint(doorTargetPosition); // remember where we told the servo to go (so the toggling works)
//    }
//
//    protected boolean isFinished()
//    {
//        // servos only need to be told once to go to their position so this command ends once execute() is done
//        return true;
//    }
//
//    protected void end()
//    {
//    }
//
//    protected void interrupted()
//    {
//    }
//}
