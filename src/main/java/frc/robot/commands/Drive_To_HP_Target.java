/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.libraries.MovingAverage;

public class Drive_To_HP_Target extends Command {

  /* @TODO - Create shuffleboard utility to tweak PID values */

  private static double STEER_kP;
  private static double STEER_kI;
  private static double STEER_kD;
  private static double DRIVE_kP;
  private static double DRIVE_kI;
  private static double DRIVE_kD;
  private static double DESIRED_TARGET_AREA;
  private static double DESIRED_TARGET_STEER_ANGLE;
  private static double MAX_DRIVE;
  private static double STEER_ERROR_THRESHOLD;
  
  private static double current_time, prev_time;
  private static double drive_error, prev_drive_error = 0, i_drive_error, d_drive_error;
  private static double steer_error, prev_steer_error = 0, i_steer_error, d_steer_error;
  private static final int MOVING_AVG_SIZE = 4;
  private static MovingAverage drive_moving_avg_list = new MovingAverage(MOVING_AVG_SIZE);
  private static MovingAverage steer_moving_avg_list = new MovingAverage(MOVING_AVG_SIZE);

  private Timer timer;

  public Drive_To_HP_Target() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_limelight_camera_subsystem);
    requires(Robot.m_drivetrain_subsystem);

    /* Start a timer for differential error */
    timer = new Timer();
    timer.start();  // Start the timer

    STEER_kP = Robot.steer_p_nt.getDouble(0.03);
    STEER_kI = Robot.steer_i_nt.getDouble(0);
    STEER_kD = Robot.steer_d_nt.getDouble(0);
    DRIVE_kP = Robot.drive_p_nt.getDouble(0.26);
    DRIVE_kI = Robot.drive_i_nt.getDouble(0);
    DRIVE_kD = Robot.drive_d_nt.getDouble(0);
    DESIRED_TARGET_AREA = Robot.desired_target_area_nt.getDouble(5);
    DESIRED_TARGET_STEER_ANGLE = Robot.desired_target_steer_angle_nt.getDouble(0);
    MAX_DRIVE = Robot.max_drive_nt.getDouble(0.7);
    STEER_ERROR_THRESHOLD = Robot.steer_error_threshold_nt.getDouble(0.0037);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    /* Grab current time */
    current_time = timer.get();

    double tx = Robot.m_limelight_camera_subsystem.Get_X();
    double ta = Robot.m_limelight_camera_subsystem.Get_Area();

    /* PID for driving to HP Target */
    drive_moving_avg_list.addToMovingAverage(ta);
    steer_moving_avg_list.addToMovingAverage(tx);

    double drive_moving_average = drive_moving_avg_list.getMovingAverage();
    double steer_moving_average = steer_moving_avg_list.getMovingAverage();

    double elapsed_time = current_time - prev_time;

    // Start with PID steering //
    steer_error = (DESIRED_TARGET_STEER_ANGLE - steer_moving_average) / 27; // Scale +-27 deg FOV to +-1 for steering command
    d_steer_error = (steer_error - prev_steer_error) / elapsed_time;
    i_steer_error += steer_error;
    double steer_cmd = (STEER_kP * steer_error) + (STEER_kI * i_steer_error) + (STEER_kD * d_steer_error);

    /* Error threshold - If we're within the error threshold, then we don't steer the robot */
    if(Math.abs(steer_error) < STEER_ERROR_THRESHOLD){
      steer_cmd = 0;
    }
    
    // try to drive forward using PID until the target area reaches our desired area //
    drive_error = DESIRED_TARGET_AREA - drive_moving_average;
    d_drive_error = (drive_error - prev_drive_error) / elapsed_time;
    i_drive_error += drive_error;
    double drive_cmd = (DRIVE_kP * drive_error) + (DRIVE_kI * i_drive_error) + (DRIVE_kD * d_drive_error);

    prev_drive_error = drive_error;
    prev_steer_error = steer_error;
    prev_time = current_time;

    // don't let the robot drive too fast into the goal
    if (drive_cmd > MAX_DRIVE) {
      drive_cmd = MAX_DRIVE;
    }

    Robot.m_drivetrain_subsystem.ArcadeDrive(drive_cmd, steer_cmd);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(drive_moving_avg_list.getMovingAverage() >= DESIRED_TARGET_AREA){
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
