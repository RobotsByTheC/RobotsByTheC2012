/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands.driving;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.templates.commands.CommandBase;
import edu.wpi.first.wpilibj.templates.subsystems.Sensors;

/**
 *
 * @author Halloran Film Studio
 */
public class BalanceFromOnRampLongWayCommand extends PIDCommand{
    
    private static final double balancingKP = 1;
    private static final double balancingKI = 0;
    private static final double balancingKD = 0;
    
    private static final double tollerance = 0.01;
    
    private Sensors sensors;
    
    private double[] balance;
    private double time;
    
    public BalanceFromOnRampLongWayCommand(){
        super(balancingKP,balancingKI,balancingKD);
        this.requires(CommandBase.driveBaseSubsystem);
        this.setSetpoint(1);
        sensors=CommandBase.sensors;
        balance = new double[100];
        for(int i=0; i<balance.length; i++)
            balance[i]=0;
        time=0;
    }

    protected double returnPIDInput() {
        return sensors.getZAcceleration();
    }

    protected void usePIDOutput(double output) {
        CommandBase.driveBaseSubsystem.getBestRobotDrive().drive(output, 0);
    }

    protected void initialize() {
    }

    protected void execute() {
        if(Timer.getFPGATimestamp()-time>.01){
            double temp=balance[balance.length-2];
            for(int i=balance.length-1; i>1; i--){
                balance[i]=temp;
                temp=balance[i-2];
            }
            balance[0]=sensors.getZAcceleration();
            time=Timer.getFPGATimestamp();
        }
    }

    protected boolean isFinished() {
        for(int i=0; i<balance.length; i++)
            if(balance[i]>tollerance)
                return false;
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
