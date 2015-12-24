package edu.wpi.first.wpilibj;

public class Elevator{

    private Jaguar  liftJaguar;
    private Encoder liftEncoder;
    
    /************************************************
     * Note: Make all measurements in INCHES. Thanks.
     ************************************************/

    private int wut = 1; //All "wut"s are just placeholders for unknown values.
    
    private final int k_ClicksPerSpin   = wut; //Number of encoder clicks per one revolution
    
    private final double k_DrumDiameter = wut; //Inches
    private final double k_ElevatorAngle = wut; //Angle of the elevator in relation to the floor (radians)
    
    private PIDController liftPID;
    private double k_P = 0.2,
                   k_I = 0.0,
                   k_D = 0.0;
    
    /*
     * Measurements are from the floor.
     */
    private final double k_MinHeight       = 0;   //Inches (this, collectionHeight, and startingHeight are probably the same values)
    private final double k_MaxHeight       = wut; //Inches
    private final double collectionHeight  = wut; //Inches
    private final double startingHeight    = wut; //Inches above the ground the cartridge starts at (measure from floor to bottom)
    
    /*Heights of each hoop off the floor*/
    private final int Hoop1_Height = 28,
                      Hoop2_Height = 61,
                      Hoop3_Height = 98;
    
    public Elevator(int jagSlot, Encoder enc){
        liftJaguar = new Jaguar(jagSlot);
        liftEncoder = enc;
        liftEncoder.setPIDSourceParameter(Encoder.PIDSourceParameter.kDistance);
        liftEncoder.start();
        
        liftPID = new PIDController(k_P, k_I, k_D, liftEncoder, liftJaguar);
        liftPID.enable();
        liftPID.setInputRange(k_MinHeight * k_ClicksPerSpin,
                              k_MaxHeight * k_ClicksPerSpin);
        liftPID.setTolerance(1);
    }
    
    /**
     * @param height Height you want the elevator to go to in inches.
     * Measured from the floor.
     */
    public void goToHeight(double height){
        if(height > k_MaxHeight)
            height = k_MaxHeight;
        if(height < k_MinHeight)
            height = k_MinHeight;
        
        liftPID.setSetpoint(height);
    }
    
    /**
     * Moves the elevator up/down at the given Jaguar speed.
     * @param speed Speed of motion.
     */
    public void move(double speed){
        if(getHeight() < k_MaxHeight && getHeight() > k_MinHeight)
            liftJaguar.set(speed);
        else
            liftJaguar.set(0);
    }
    
    /**
     * Dumps held balls.
     */
    public void dump(){
        
    }
    
    public void dumpAtOne(){
        goToHeight(Hoop1_Height + 6);
        dump();
    }
    
    public void dumpAtTwo(){
        goToHeight(Hoop1_Height + 6);
        dump();
    }
 
    /**
     * Extends the elevator to its maximum height.
     */
    public void fullExtend(){
        goToHeight(k_MaxHeight);
    }
    
    /**
     * Retracts the elevator to its lowest height.
     */
    public void fullRetract(){
        goToHeight(k_MinHeight);
    }
    
    /**
     * Gets the current height of the ball cartridge above the ground in inches.
     * @return The current height of the ball cartridge above the ground.
     */
    public double getHeight(){
        return Math.sin(k_ElevatorAngle) * (Math.PI * k_DrumDiameter) * (liftEncoder.getRaw() / k_ClicksPerSpin) + startingHeight; //Circumference * revolutions + starting height
    }
    
    /**
     * Moves the ball cartridge to a height that's safe for bump traversal.
     */
    public void goToBumpHeight(){
        goToHeight(8);
    }
    
    /**
     * Keeps the elevator at the same height.
     * If the elevator doesn't slide down by itself, this shouldn't need to be used.
     */
    public void stayAtHeight(){
        liftPID.setSetpoint(getHeight());
    }
    
    /**
     * Checks if the ball cartridge is out of range to be filled
     * @return true if yes, else false.
     */
    public boolean isOutOfCollectionRange(){
        boolean ret = true;
        if(getHeight() != collectionHeight)
            ret = true;
        else
            ret = false;
        return ret;
    }
    
    /**
     * Returns minimum programmed height
     * @return Minimum programmed height
     */
    public double getMinHeight(){
        return k_MinHeight;
    }
    
    /**
     * Returns maximum programmed height
     * @return Maximum programmed height
     */
    public double getMaxHeight(){
        return k_MaxHeight;
    }
}
