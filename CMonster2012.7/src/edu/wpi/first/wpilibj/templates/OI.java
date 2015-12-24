
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.KinectStick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.templates.commands.CommandBase;
import edu.wpi.first.wpilibj.templates.commands.SwitchRobotFrontCommand;
import edu.wpi.first.wpilibj.templates.commands.ZeroPitchGyroCommand;
import edu.wpi.first.wpilibj.templates.commands.driving.*;
import edu.wpi.first.wpilibj.templates.commands.dumper.*;
import edu.wpi.first.wpilibj.templates.commands.elevator.LowerElevatorCommand;
import edu.wpi.first.wpilibj.templates.commands.elevator.ManualElevatorControlWithPauseCommandGroup;
import edu.wpi.first.wpilibj.templates.commands.elevator.PutElevatorInStoredPositionAfterFillingChamberCommand;
import edu.wpi.first.wpilibj.templates.commands.elevator.RaiseElevatorCommand;
import edu.wpi.first.wpilibj.templates.commands.ramp.*;
import edu.wpi.first.wpilibj.templates.commands.sweeper.ToggleSweeperCommand;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // Another type of button you can create is a DigitalIOButton, which is
    // a button or switch hooked up to the cypress module. These are useful if
    // you want to build a customized operator interface.
    // Button button = new DigitalIOButton(1);
    
    // There are a few additional built in Buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
    
    Joystick stick, testStick;
    
    KinectStick leftArm, rightArm;
    
    Button mecanumModeButton,
           quickCrabLeftButton,
           quickCrabRightButton,
           resetPitchGyroButton,
           balanceFromOnRampLongWay,
           //toggleSweeperStateUpButton,
           //toggleSweeperStateDownButton,
           toggleSweeperStateButton,
           joystick1RaiseElevatorButton,
           resetPitchGyroSmartDashboardButton,
           switchRobotFrontCommandButton,
           rampLowerButton,
           manualElevatorOperationButton,
           dumpAndResetBallsButton,
           manualRampControlButton,
           doorServoOneWay,
           doorServoOtherWay;
    
    //Kinect Buttons
    
    Button kinectDumpBallsButtonLeftFoot,
           kinectDumpBallsButtonRightFoot;
    
    Button raiseDumperRamp,
           lowerDumperRamp,
           openDumperDoor,
           closeDumperDoor,
           raiseElevator,
           raiseRampController,
           lowerRampController,
           raiseElevatorSD,
           lowerElevatorSD,
           middleElevatorSD,
           lowerAndHoldRampSD;
    
    HybridPeriodKinectModeShifter kinectShifter;
    
    private boolean invertX,
                    invertY;
    
    private Button closeRearDumperDoor,
                   openReadRumperDoor,
                   closeServoABitButton,
                   openServoABitButton;
    
    private Button driveForwardButton;

    public OI() {
        
        invertX = false;
        invertY = true;
        
        stick = new Joystick(1);
        testStick = new Joystick(2);
        leftArm = new KinectStick(1);
        rightArm = new KinectStick(2);
        
   //     kinectDumpBallsButtonLeftFoot = new KinectButton(leftArm, RobotMap.Buttons.KinectButtons.kinectDumpBallsButtonLeftFoot);
   //     kinectDumpBallsButtonRightFoot = new KinectButton(leftArm, RobotMap.Buttons.KinectButtons.kinectDumpBallsButtonRightFoot);
        
  //      kinectDumpBallsButtonLeftFoot.whenPressed(new DumpBallsAndResetCommandGroup());
  //      kinectDumpBallsButtonRightFoot.whenPressed(new DumpBallsAndResetCommandGroup());
        
        mecanumModeButton = new JoystickButton(stick, RobotMap.Buttons.mecanumModeJoystickButton);
        quickCrabLeftButton = new JoystickButton(stick, RobotMap.Buttons.quickCrabLeftButton);
        quickCrabRightButton = new JoystickButton(stick, RobotMap.Buttons.quickCrabRightButton);
        resetPitchGyroButton = new JoystickButton(stick, RobotMap.Buttons.resetPitchGyroButton);
        //balanceFromOnRampLongWay = new JoystickButton(stick, RobotMap.Buttons.balanceFromOnRampLongWay);
        //toggleSweeperStateUpButton = new JoystickButton(stick, RobotMap.Buttons.toggleSweeperStateUpButton);
        //toggleSweeperStateDownButton = new JoystickButton(stick, RobotMap.Buttons.toggleSweeperStateDownButton);
        toggleSweeperStateButton = new JoystickButton(stick, RobotMap.Buttons.toggleSweeperStateDownButton);
        joystick1RaiseElevatorButton = new JoystickButton(stick, RobotMap.Buttons.toggleSweeperStateUpButton);
        resetPitchGyroSmartDashboardButton = new SmartDashboardButton("Reset Pitch Gyro");
        switchRobotFrontCommandButton = new JoystickButton(stick, RobotMap.Buttons.switchRobotFrontCommandButton);
        rampLowerButton = new JoystickButton(stick, RobotMap.Buttons.rampLowerButton);
        manualElevatorOperationButton = new JoystickButton(stick, RobotMap.Buttons.manualElevatorOperationButton);
        dumpAndResetBallsButton = new JoystickButton(stick, RobotMap.Buttons.DumpAndResetBallsCommand);
        manualRampControlButton = new JoystickButton(testStick, 3);
//        doorServoOneWay = new JoystickButton(testStick, 8);
//        doorServoOtherWay = new JoystickButton(testStick, 9);
        
//        testInternalButton = new InternalButton();
//        testInternalButton.whenPressed(new ZeroPitchGyroCommand());
        
        
        mecanumModeButton.whileHeld(new MecanumDriveCommand());
        quickCrabLeftButton.whileHeld(new QuickCrabLeftCommand());
        quickCrabRightButton.whileHeld(new QuickCrabRightCommand());
        resetPitchGyroButton.whenPressed(new ZeroPitchGyroCommand());
        //balanceFromOnRampLongWay.whenPressed(new BalanceFromOnRampLongWayCommand());
        //toggleSweeperStateUpButton.whenPressed(new ToggleSweeperStateUpCommand());
        //toggleSweeperStateDownButton.whenPressed(new ToggleSweeperStateDownCommand());
        toggleSweeperStateButton.whenPressed(new ToggleSweeperCommand());
        joystick1RaiseElevatorButton.whenPressed(new RaiseElevatorCommand());
        resetPitchGyroSmartDashboardButton.whenPressed(new ZeroPitchGyroCommand());
        switchRobotFrontCommandButton.whenPressed(new SwitchRobotFrontCommand());
        rampLowerButton.whenPressed(new ToggleRampControllerCommand());
        manualElevatorOperationButton.whenPressed(new ManualElevatorControlWithPauseCommandGroup());
        //manualElevatorOperationButton.whenReleased(new StopElevatorCommand());
        dumpAndResetBallsButton.whenPressed(new DumpBallsAndResetCommandGroup());
        
        manualRampControlButton.whileHeld(new ManualRampControlCommand());
        
//        doorServoOneWay.whileHeld(new DoorServoManualControl(false));
//        doorServoOtherWay.whileHeld(new DoorServoManualControl(true));
        
        
        
        raiseDumperRamp = new JoystickButton(testStick, 8);
        lowerDumperRamp = new JoystickButton(testStick, 9);
        openDumperDoor = new JoystickButton(testStick, 6);
        closeDumperDoor = new JoystickButton(testStick, 7);
        closeRearDumperDoor = new JoystickButton(testStick, 10);
        openReadRumperDoor = new JoystickButton(testStick, 11);
        
        closeRearDumperDoor.whenPressed(new CloseRearDumperDoorCommand());
        openReadRumperDoor.whenPressed(new OpenRearDumperDoorCommand());
        
        raiseDumperRamp.whenPressed(new RaiseDumperRampCommand());
        lowerDumperRamp.whenPressed(new LowerDumperRampCommand());
        openDumperDoor.whenPressed(new OpenDumperDoorCommand());
        closeDumperDoor.whenPressed(new CloseDumperDoorCommand());
        
        
        
        raiseElevator = new JoystickButton(testStick, 1);
        raiseElevator.whenPressed(new RaiseElevatorCommand());
        
        
        raiseRampController = new SmartDashboardButton("Raise Ramp Controller");
        lowerRampController = new SmartDashboardButton("Lower Ramp Controller");
        
        raiseRampController.whenPressed(new RaiseRampControllerCommand());
        lowerRampController.whenPressed(new LowerRampControllerCommand());
        
        raiseElevatorSD = new SmartDashboardButton("Raise Elevator Full Height");
        lowerElevatorSD = new SmartDashboardButton("Lower Elevator to Floor");
        middleElevatorSD = new SmartDashboardButton("Put Elevator in middle height");
        
        raiseElevatorSD.whenPressed(new RaiseElevatorCommand());
        lowerElevatorSD.whenPressed(new LowerElevatorCommand());
        middleElevatorSD.whenPressed(new PutElevatorInStoredPositionAfterFillingChamberCommand());
        
        lowerAndHoldRampSD = new SmartDashboardButton("Lower And Hold Ramp");
        lowerAndHoldRampSD.whenPressed(new LowerRampAndHoldDownCommandGroup());
        
//        closeServoABitButton = new SmartDashboardButton("Close Servo A Bit");
//        openServoABitButton = new SmartDashboardButton("Open Servo A Bit");
//        closeServoABitButton.whenPressed(new CloseServoABitCommand(CommandBase.dumperSubsystem.getRearDoorServo()));
//        openServoABitButton.whenPressed(new OpenServoABitCommand(CommandBase.dumperSubsystem.getRearDoorServo()));
        
        kinectShifter = new HybridPeriodKinectModeShifter();
        
        driveForwardButton = new JoystickButton(stick, 8);
        driveForwardButton.whenPressed(new DriveUntilStoppedCommand());
    }

    public Joystick getStick() {
        return stick;
    }

    public Joystick getTestStick() {
        return testStick;
    }
    
    
    
//    public Button getManualElevatorOperationButton(){
//        return manualElevatorOperationButton;
//    }
    
    public boolean overrideEnabled(){
        return (stick.getZ()<-.2);
    }
    
    public double getJoystickX(){
        return (invertX)?-stick.getX():stick.getX();
    }
    
    public double getUninvertedJoystickX(){
        return stick.getX();
    }
    
    public double getJoystickY(){
        return (!invertY)?-stick.getY():stick.getY();
    }
    
    public double getUninvertedJoystickY(){
        return stick.getY();
    }
    
    public KinectStick getLeftArm(){
        return leftArm;
    }
    
    public KinectStick getRightArm(){
        return rightArm;
    }
    
    public void invertJoystickY(){
        invertY=!invertY;
    }
    
    public void invertJoystickX(){
        invertX=!invertX;
    }
    
    public boolean isJoystickXInverted(){
        return invertX;
    }

    public boolean isJoystickYInverted() {
        return invertY;
    }
    
    public HybridPeriodKinectModeShifter getKinectShifter()
    {
        return kinectShifter;
    }
}

