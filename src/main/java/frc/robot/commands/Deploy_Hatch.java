/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class Deploy_Hatch extends CommandGroup {
  /**
   * Add your docs here.
   */
  public Deploy_Hatch() {
    // Add Commands here:
    // e.g. addSequential(new Command1());
    // addSequential(new Command2());
    // these will run in order.

    // To run multiple commands at the same time,
    // use addParallel()
    // e.g. addParallel(new Command1());
    // addSequential(new Command2());
    // Command1 and Command2 will run in parallel.

    // A command group will require all of the subsystems that each member
    // would require.
    // e.g. if Command1 requires chassis, and Command2 requires arm,
    // a CommandGroup containing them would require both the chassis and the
    // arm.
   
   /* 
    - Pseudocode
      1. moveback 1secs
      2. movehatch forward
      3. deploy pistons
      4. wait for a little bit
      5. pull back pistons
      6. movehatch back
    */

    /* Change these variables here for changing the amount of time (in seconds) */
    // Move backward speed + time //
    double driveBackwardTime = 0.2;
    double driveBackwardSpeed = 0.5;

    // Foward hatch moving speed + time //
    double hatchForwardTime = 0.2;
    double hatchForwardSpeed = 0.5;

    // Retract piston wait time //
    double waitTime = 0.5;

    // Backward hatch moving speed + time //
    double hatchBackwardTime = 0.2;
    double hatchBackwardSpeed = 0.5;

    addSequential(new Manual_Drive_Backward(driveBackwardTime, driveBackwardSpeed));
    addSequential(new HatchPanel_Forward(hatchForwardTime, hatchForwardSpeed));
    addSequential(new Piston_Deploy());
    addSequential(new WaitCommand(waitTime));
    addSequential(new Piston_Retract());
    addSequential(new HatchPanel_Forward(hatchBackwardTime, hatchBackwardSpeed));
  }
}