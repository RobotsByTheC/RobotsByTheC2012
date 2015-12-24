package edu.wpi.first.wpilibj.templates;

/**
 * The RobotMap is a mapping from the Ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static final int leftMotor = 1;
    // public static final int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static final int rangefinderPort = 1;
    // public static final int rangefinderModule = 1;
    
    //PINS and PORTS:
    public static class Ports{
        public static class PWM{
            public static final int jaguarFrontLeft = 4; //SET
            public static final int jaguarRearLeft = 3;
            public static final int jaguarFrontRight = 5;
            public static final int jaguarRearRight = 1; //SET

            public static final int jaguarSweeper = 2;   //SET 

            public static final int jaguarElevator = 6;  //SET
            
            public static int victorRampLowerer = 7;     //SET
            
            public static int servoElevatorDoor = 8,
                              servoRearElevatorDoor = 10;
            
            public static int servoDumperRamp1 = 9;
        }
        
        public static class Analog{
            
            public static final int yawGyroPort = 2;
            public static final int pitchGyroPort = 1;
            public static final int accelerometerXSlot = 4;
            public static final int accelerometerYSlot = 5;
            public static final int accelerometerZSlot = 6;
            public static final int ultrasonicSlot = 3;
            //public static final int analogElevatorSensorPort = 7;
            
            //Analog Ports being used for digital devices, because that's how we roll
            //They all go on the second analog module
            
            public static final int ballSensor1Port  = 1,
                                    ballSensor2Port = 2,
                                    ballSensor3Port = 3;
            
//            public static final int elevatorSensor1Port = 4,
//                                    elevatorSensor2Port = 5,
//                                    elevatorSensor3Port = 6;
            
            public static final int rampControllerExtendedLimitSwitchButton = 4,
                                    rampControllerStoredLimitSwitch = 5;
            
        }
        
        public static class Digital{

            public static final int firstStageUpperLimitSwitchPort = 11, //Need to be set
                                    firstStageLowerLimitSwitchPort = 12, //...
                                    secondStageUpperLimitSwitchPort = 13,//...
                                    secondStageLowerLimitSwitchPort = 14;//...

            public static final int[] encoderFrontLeft  = {1, 2},
                                      encoderRearLeft   = {3, 4},
                                      encoderRearRight  = {5, 6},
                                      encoderFrontRight = {7, 8},
                                      encoderElevator   = {9, 10};
        }
    }
    
    //BUTTONS:
    public static class Buttons{
        public static final int mecanumModeJoystickButton = 3,
                                quickCrabLeftButton = 4,
                                quickCrabRightButton = 5,
                                resetPitchGyroButton = 8,
                                balanceFromOnRampLongWay = 9,
                                toggleSweeperStateUpButton = 11,
                                toggleSweeperStateDownButton = 10,
                                switchRobotFrontCommandButton = 7,
                                rampLowerButton = 6,
                                manualElevatorOperationButton = 2,
                                DumpAndResetBallsCommand = 1; // trigger
        
        public static class KinectButtons{
            public static final int kinectDumpBallsButtonLeftFoot = 7,  //kick left foot forward
                                    kinectDumpBallsButtonRightFoot = 5; //kick right foot forward
        }
    }
    
}
