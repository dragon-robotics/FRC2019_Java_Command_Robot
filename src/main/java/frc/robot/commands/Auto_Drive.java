/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Auto_Drive extends CommandGroup {
  /**
   * Add your docs here.
   */
  public Auto_Drive(int level, int direction) {
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

    

    // Choose time and speed for robot to drive forward //
    double forward_time = 1;
    double forward_speed = 1;

    if(level == 1){
      double lvl2_forward_time = 0.5;
      double lvl2_forward_speed = 0.8;
      addSequential(new Manual_Drive_Forward(lvl2_forward_time, lvl2_forward_speed));
    }
    
    addSequential(new Manual_Drive_Forward(forward_time, forward_speed));
    
    /* Drive path to the 
        Left     Right
          |        |
          |        |
          \        /
           \      /
           |      |
           |      |
    ===============================
    */

    double steering_time = 0.5;
    double drive_time = 1;
    double steer_amount = 0.5;

    addSequential(new Tank_Drive(drive_time, 1, 1));
    addSequential(new Tank_Drive(steering_time, 1 - (direction * steer_amount), steer_amount + (direction * steer_amount)));
    addSequential(new Tank_Drive(steering_time, steer_amount + (direction * steer_amount), 1 - (direction * steer_amount)));
    addSequential(new Tank_Drive(drive_time, 1, 1));

    /* Find the HP target */
    addSequential(new Find_HP_Target(direction));

    /* (RIGHT SIDE LEVEL 2) Move Backwards for .7 seconds at half speed
    move backwards for 2 seconds at full speed 
    do a 90 degree turn torwards the right
    make sure we are alligned with the limelight
    deploy pistons out
    do a 100 degree turn to the  left
    go forward for 1.5 seconds full to get the ball
    intake the ball
    move backwards for 1.7 seconds full speed
    do a a 90 degree turn to the right
    move the elevator up at full speed for x second( x= appropriate height)
    outtake cargo*/

    /* (Left side  SIDE LEVEL 2) Move Backwards for .7 seconds at half speed
    move backwards for 2 seconds at full speed 
    do a 90 degree turn torwards the left
    make sure we are alligned with the limelight
    deploy pistons out
    do a 100 degree turn to the  right
    go forward for 1.5 seconds full to get the ball
    intake the ball
    move backwards for 1.7 seconds full speed
    do a a 90 degree turn to the left
    move the elevator up at full speed for x second( x= appropriate height)
    outtake cargo*/
  }
}
