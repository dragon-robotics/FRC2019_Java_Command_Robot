/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.LinkedList;
import java.util.Queue;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class Drive_To_HP_Target extends Command {

  private static final double STEER_P = 0.03;
  private static final double STEER_I = 0;
  private static final double STEER_D = 0;
  private static final double DRIVE_P = 0.26;
  private static final double DRIVE_I = 0;
  private static final double DRIVE_D = 0;
  private static final double DESIRED_TARGET_AREA = 13.0;
  private static final double MAX_DRIVE = 0.7;
  
  private static double error, prev_error = 0, i_error, d_error;
  private static final int MOVING_AVG_SIZE = 4;
  private static Queue<Double> moving_avg_list = new LinkedList<>();

  public Drive_To_HP_Target() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_limelight_camera_subsystem);
    requires(Robot.m_drivetrain_subsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double tv = Robot.m_limelight_camera_subsystem.Find_Target();
    double tx = Robot.m_limelight_camera_subsystem.Get_X();
    double ty = Robot.m_limelight_camera_subsystem.Get_Y();
    double ta = Robot.m_limelight_camera_subsystem.Get_Area();

    /* PID for driving to HP Target */

    // if(moving_avg_list.size() < MOVING_AVG_SIZE){
    //   moving_avg_list.add(tx);
    // }
    // else{
    //   moving_avg_list.remove();
    //   moving_avg_list.add(tx);
    // }
    
    // double moving_sum = 0;
    // for(double value : moving_avg_list){
    //   moving_sum += value;
    // }
    // double moving_average = moving_sum / moving_avg_list.size();
    // error = moving_average / 27;  // Needs to be divided by 27 because 27 degrees is the max FOV and we need to scale the error from 1 to -1 for steering.
    // d_error = error - prev_error;
    // i_error += error;

    // Start with proportional steering
    double steer_cmd = tx * STEER_P;

    // try to drive forward until the target area reaches our desired area
    double drive_cmd = (DESIRED_TARGET_AREA - ta) * DRIVE_P;

    // don't let the robot drive too fast into the goal
    if (drive_cmd > MAX_DRIVE) {
      drive_cmd = MAX_DRIVE;
    }

    Robot.m_drivetrain_subsystem.TeleopDrive(drive_cmd, steer_cmd);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    double ta = Robot.m_limelight_camera_subsystem.Get_Area();

    if(ta >= DESIRED_TARGET_AREA){
      return true;
    }
    else{
      return false;
    }
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
