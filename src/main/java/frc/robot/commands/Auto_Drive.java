/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;

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
    double forward_time = Robot.forward_time_nt.getDouble(1);
    double forward_speed = Robot.forward_speed_nt.getDouble(0.8);

    if(level == 1){
      double lvl2_forward_time = Robot.lvl2_forward_time_nt.getDouble(0.5);
      double lvl2_forward_speed = Robot.lvl2_forward_speed_nt.getDouble(0.8);
      addSequential(new Manual_Drive_Forward(lvl2_forward_time, lvl2_forward_speed));
    }

    addSequential(new Manual_Drive_Forward(forward_time, forward_speed));
    
    /* 
      Drive path to the HP
        Left     Right
          |        |
          |        |
          \        /
            \      /
            |      |
            |      |
      ==================
    */

    // double drive_time = 1;
    // double drive_amount = 0.7;
    // double drive_path_steering_time = 0.5;
    // double drive_path_steer_amount = 0.1;

    double drive_time = Robot.drive_time_nt.getDouble(1);
    double drive_amount = Robot.drive_amount_nt.getDouble(0.7);
    double drive_path_steering_time = Robot.drive_path_steering_time_nt.getDouble(0.5);
    double drive_path_steer_amount = Robot.drive_path_steer_amount_nt.getDouble(0.1);
    
    addSequential(new Arcade_Drive(drive_time, drive_amount, 0));
    addSequential(new Arcade_Drive(drive_path_steering_time, drive_amount, drive_path_steer_amount));
    addSequential(new Arcade_Drive(drive_path_steering_time, drive_amount, -drive_path_steer_amount));
    addSequential(new Arcade_Drive(drive_time, drive_amount, 0));
    
    /* Find the HP target */
    double find_target_steer_amount = Robot.find_target_steer_amount_nt.getDouble(0.5);
    addSequential(new Find_HP_Target(direction, find_target_steer_amount));

    addSequential(new Drive_To_HP_Target());
    addSequential(new Piston_Deploy());
    
    double backward_time = Robot.backward_time_nt.getDouble(0.3);
    double backward_speed = Robot.backward_speed_nt.getDouble(0.8);
    addSequential(new Manual_Drive_Backward(backward_time, backward_speed));

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
