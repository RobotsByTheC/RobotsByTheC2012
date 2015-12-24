package edu.wpi.first.wpilibj;

public class BestRobotDrive extends RobotDrive{

    double distance;

    public BestRobotDrive(int FL, int FR, int RL, int RR){
        super(FL, FR, RL, RR);
    }

    public void mecanumDrive(Joystick j)
    {
        double jX = j.getX();
        double jY = j.getY();

        /********** our custom Mecanum drive *******/
        DriverStationLCD.getInstance().println(DriverStationLCD.Line.kMain6, 1, "Mecanum Drive Engaged");
        jX = -jX; //This is put in to reverse the X axis and fix the inverted left to right crab.

        // CHRIS WILLIAMS's MECANUM MAGIC
        jaguarDrive(-(jX+jY)/2, -(jY-jX)/2, -(jY-jX)/2, -(jX+jY)/2);
        //          FrontLeft, FrontRight, RearLeft , RearRight
    }


    /**
     *
     * @param FL front left speed
     * @param FR front right speed
     * @param RL rear left speed
     * @param RR rear right speed
     */
    public void jaguarDrive(double FL, double FR, double RL, double RR)
    {
        m_frontLeftMotor.set(-FL);
        m_frontRightMotor.set(FR);
        m_rearLeftMotor.set(-RL);
        m_rearRightMotor.set(RR);
    }

    public void crabLeft(double speed)
    {
        double jX = -0.75;
        double jY = 0;
        jX = -jX;
        jaguarDrive(-(jX+jY)/2, -(jY-jX)/2, -(jY-jX)/2, -(jX+jY)/2);
    }

    public void crabRight(double speed)
    {
        double jX = 0.75;
        double jY = 0;
        jX = -jX;
        jaguarDrive(-(jX+jY)/2, -(jY-jX)/2, -(jY-jX)/2, -(jX+jY)/2);
    }

    public void circleDrive(double speed)
    {

        double turnSpeed;
        if(Math.abs(speed) > 0.4){
            if(speed>0)
                turnSpeed = 0.4;
            else
                turnSpeed = -0.4;
        }
        else
            turnSpeed = speed*0.8;
        //turnSpeed = speed*0.4;
        jaguarDrive(turnSpeed, -turnSpeed, turnSpeed, -turnSpeed);
    }

    public void driveDistance(double goalDistance, double speed, int clicksPerSpin, double wheelDiameter, Encoder encoder){
        /* The parameters are as follows
         * Goal Distance is how far the robot should move IN INCHES
         * Speed is how fast the robot should move when traveling that distance (-1.0 is full reverse, 1.0 is full forward)
         * Clicks Per Spin is the number of clicks the encoder reports in one rotation of the wheel
         * wheelDiameter the diameter of the robot wheel, it is used to calculate circumference
         * Encoder is the encoder created in the main method that is used for everything
         */
        distance = Math.abs(encoder.getRaw())*((wheelDiameter*Math.PI)/clicksPerSpin); //This only measures distance traveled, distance backwards should count to this number
        if(distance<=goalDistance)
            jaguarDrive(speed, speed, speed, speed);
    }

    public void driveDistance(double goalDistance, double speed, Encoder encoder){
        driveDistance(goalDistance, speed, 1500, 8, encoder);
    }

    public void driveDistance(double goalDistance, Encoder encoder){
        driveDistance(goalDistance, 0.5, encoder);
    }
    
    /**
     * Uses the full capability of Mecanum drive.
     * @param  forwardSpeed  Speed the robot moves in a vertical direction (not robot-oriented)
     * @param crabRightSpeed Speed the robot moves in a lateral direction (not robot-oriented)
     * @param clockwiseSpeed Speed the robot turns around it's z-axis (is robot-oriented)
     */
    public void proveThatMecanumsAreAwesome(double forwardSpeed, double crabRightSpeed, double clockwiseSpeed){
        final int FL = 1, 
                  FR = 2,
                  RL = 3,
                  RR = 4;
        double[] motorSpeeds = {forwardSpeed + crabRightSpeed + clockwiseSpeed,  //FL
                                forwardSpeed - crabRightSpeed - clockwiseSpeed,  //FR
                                forwardSpeed - crabRightSpeed + clockwiseSpeed,  //RL
                                forwardSpeed + crabRightSpeed - clockwiseSpeed}; //RR
        normalize(motorSpeeds);
        jaguarDrive(motorSpeeds[FL],
                    motorSpeeds[FR],
                    motorSpeeds[RL],
                    motorSpeeds[RR]);
    }
    
    public void proveThatMecanumsAreAwesome(Joystick joy){
        proveThatMecanumsAreAwesome(joy.getY(), joy.getX(), 0);
    }

}