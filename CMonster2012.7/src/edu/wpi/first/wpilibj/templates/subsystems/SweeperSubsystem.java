/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.commands.sweeper.SweeperMonitorCommand;

/**
 *
 * @author
 * peter
 */
public class SweeperSubsystem extends Subsystem{
    
    //public final double sweeperSpeed=1;
    
    private Jaguar jaguar;
    
    private SweeperState currentState;
    
    public static class SweeperState{
        private static final double sucking_val = 0.75,
                                    stopped_val = 0,
                                    spitting_val = -0.75;
        public final double val;
        public final String state;
        
        public static final SweeperState sucking = new SweeperState(sucking_val, "sucking"),
                                         stopped = new SweeperState(stopped_val, "stopped"),
                                         spitting = new SweeperState(spitting_val, "spitting");
        public double getVal(){
            return val;
        }
        
        public String printState(){
            return state;
        }
        
        private SweeperState(double x, String str){
            this.val = x;
            this.state = str;
        }
    }
    
    public SweeperSubsystem(){
        jaguar = new Jaguar(RobotMap.Ports.PWM.jaguarSweeper);
        currentState = SweeperState.stopped;
        updateMotor();
    }

    protected void initDefaultCommand() {
        this.setDefaultCommand(new SweeperMonitorCommand());
    }
    
    public SweeperState getState(){
        return currentState;
    }
    
    public void setSweeperState(SweeperState newState){
        currentState = newState;
        updateMotor();
    }
    
    private void updateMotor(){
        jaguar.set(currentState.val);
    }
}
