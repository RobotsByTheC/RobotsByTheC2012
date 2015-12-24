package org.robotsbythec.jaguartest;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Timer;

public class JaguarTest extends SimpleRobot {
    Jaguar jag1;
    
    public void robotInit() {
       jag1 = new Jaguar(6);
    }

    public void operatorControl() {
        while(true){
            jag1.set(0.5);

            Timer.delay(10);
            
            jag1.set(0.0);
            
            Timer.delay(1);

            jag1.set(-0.5);
            
            Timer.delay(10);
        }
    }
    
}
