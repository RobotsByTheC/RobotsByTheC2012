/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands.elevator;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.commands.CommandBase;

/**
 *
 * @author Sean Halloran
 */
public class RaiseElevatorCommand extends CommandBase{
    
    double[] rates;
    double averageRate;
    private int finish;
    int timesUpdated;

    public RaiseElevatorCommand(){
        requires(elevatorSubsystem);
        rates = new double[25];
        for(int i = 0; i<rates.length; i++)
            rates[i]=0;
        finish = 0;
        timesUpdated=0;
    }
    
    protected void initialize(){
        elevatorSubsystem.setMotorSpeed(-1.0);
        rates = new double[25];
        for(int i = 0; i<rates.length; i++)
            rates[i]=0;
        finish = 0;
        timesUpdated=0;
    }

    protected void execute(){
        //double rate = elevatorSubsystem.getEncoder().getRate();
        elevatorSubsystem.setMotorSpeed(-1.0);
//        double temp=rates[rates.length-2];
//        for(int i=rates.length-1; i>1; i--){
//            rates[i]=temp;
//            temp=rates[i-2];
//        }
//        rates[0]=elevatorSubsystem.getEncoder().getRate();
//        timesUpdated++;
        double[] tempRates = new double[rates.length];
        for(int i=0; i<rates.length; i++)
            tempRates[i] = rates[i];
        rates[0] = elevatorSubsystem.getEncoder().getRate();
        for(int i=1; i<rates.length; i++)
            rates[i] = tempRates[i-1];
        averageRate = 0;
        for(int i = 0; i<rates.length; i++)
            averageRate+=rates[i];
        averageRate/=rates.length;
        timesUpdated++;
        SmartDashboard.putDouble("AverageElevatorEncoderRate", averageRate);
        SmartDashboard.putBoolean("Averages Valid", (timesUpdated>rates.length));
        //if(elevatorSubsystem.getEncoder().getRate()<150)
//        if(averageRate < 1.5 && timesUpdated < 150)
//            finish++;
        SmartDashboard.putDouble("ElevatorEncoderRate", elevatorSubsystem.getEncoder().getRate());
        if(oi.getTestStick().getRawButton(2)){
            cancel();
        }
    }

    protected boolean isFinished(){
//        if(timeSinceInitialized()<2)
//            return false;
//        return (finish > 25);
        //return elevatorSubsystem.getEncoder().getRate()<175 && timeSinceInitialized()>3;
        return (timeSinceInitialized() > 2 && averageRate < 325 && timesUpdated > rates.length);
    }

    protected void end() {
        elevatorSubsystem.setMotorSpeed(0);
        //elevatorSubsystem.setMaxHeight();
    }

    protected void interrupted(){
        elevatorSubsystem.setMotorSpeed(0);
    }
    
}
