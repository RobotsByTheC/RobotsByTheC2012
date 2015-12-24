package edu.wpi.first.wpilibj;

public class Sweeper {
    
    private Jaguar jaguar;
    private double speed = 1;
    
    /**
     * Creates a new Sweeper object
     * @param slot Slot the Jaguar is plugged into
     */
    public Sweeper(int slot){
        jaguar = new Jaguar(slot);
    }
    
    /**
     * Sweeps balls into the robot
     * @param speed Speed the sweeper spins at
     */
    public void sweep(){
        jaguar.set(speed);
    }
    
    /**
     * Prevents any more balls from coming into the robot.
     * @param speed Speed the sweeper spins at
     */
    public void reject(){
        jaguar.set(-speed);
    }
    
    /**
     * Stops the sweeper.
     */
    public void stop(){
        jaguar.set(0);
    }
    
    /**
     * Sets the speed at which the sweeper turns.
     * @param speed Usual method for setting Jaguar speed.
     */
    public void setSpeed(double speed){
        this.speed = speed;
    }
    
}
