/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.templates.commands.kinect.KinectElevatorOperationCommand;
import edu.wpi.first.wpilibj.templates.commands.kinect.KinectRampControllerOperationCommand;
import edu.wpi.first.wpilibj.templates.commands.kinect.KinectShifterUpdateCommand;
import edu.wpi.first.wpilibj.templates.commands.kinect.KinectSweeperOperationCommand;

/**
 *
 * @author
 * team 2084
 */
public class HybridPeriodKinectCommandGroup extends CommandGroup{

    public HybridPeriodKinectCommandGroup() {
        addParallel(new KinectShifterUpdateCommand());
        addParallel(new KinectElevatorOperationCommand());
        addParallel(new KinectSweeperOperationCommand());
        addParallel(new KinectRampControllerOperationCommand());
    }

    
    
}
