/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.robotsbythec;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author
 * team 2084
 * 
 * We can't have the robot do anything with images...We'll need to have public booleans and doubles and have the SmartDashboard handle the rest.
 */
public class Emotions
{
    private int maximumTextFields; // this is because some of the String[]'s have more lines than the others.
    
    public final String[] happyFace = {
      "   ----   ----   ",
      "     O     O     ",
      "        /        ",
      "       /         ",
      "      `----      ",
      "  \\_________/ ", // two backslashes show up as only one
      "    \\______/  "
    };
    
    public final String[] madFace = {
      "     \\\\  //     ",
      "      o    o     ",
      "        /        ",
      "       /         ",
      "      `----      ",
      "    /-------\\   ",
      "   /---------\\  "
    };
    
    public final String[] seriousFace = {
      "   ____   ____   ",
      "     o     o     ",
      "        /        ",
      "       /         ",
      "      `----      ",
      "                 ",
      "      ------     "
    };
    
    public final String[] raisingElevator = { // copied from http://www.geocities.com/spunk1111/bodypart.htm
      "    .''.         ",
      "    |  |         ",
      "    |  |         ",
      "    |  |         ",
      "    |  |--.--._  ",
      "    |  | _|  | `|",
      "    |  /` )  |  |",
      "    | /  /'--:__/",
      "    |/  /         |",
      "    (  '  \\       |",
      "     \\    `.     / ",
      "      |         |  ",
      "      |         |  "
    };
    
    public final String happy      = "awesome.png",
                        angry      = "angry.png",
                        neutral    = "neutral.png";
//                        up1        = "up1.png",
//                        up2        = "up2.png",
//                        up3        = "up3.png",
//                        down1      = "down1.png",
//                        down2      = "down2.png",
//                        down3      = "down3.png",
//                        motionless = "elevatorNeutral.png";
    
    public void display(String[] text)
    {
        for(int i = 0; i < text.length; i++)
        {
            SmartDashboard.putString(("f"+i), text[i]); // text box labels go like: f0 f1 etc... doesn't really matter what they're labeled
            
            if(maximumTextFields < text.length)
                maximumTextFields = text.length;
        }
        
        if(maximumTextFields > text.length)
        {
            for(int i = text.length; i < maximumTextFields; i++)
            {
                SmartDashboard.putString(("f"+i), ""); // make these text fields blank
            }
        }
    }
    
    public void display(String emotion){
        SmartDashboard.putString("", emotion);
    }
    
}
