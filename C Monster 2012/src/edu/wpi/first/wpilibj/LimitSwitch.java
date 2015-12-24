package edu.wpi.first.wpilibj;

public class LimitSwitch extends DigitalInput{
    
    public LimitSwitch(final int PWMchannel){
        super(PWMchannel);
    }
    
    public boolean isPressed(){
        return get();
    }
    
}
