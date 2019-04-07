/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.libraries.MovingAverage;

public class Drive_To_HP_Target extends Command {

  private static final double STEER_kP = 0.03;
  private static final double STEER_kI = 0;
  private static final double STEER_kD = 0;
  private static final double DRIVE_kP = 0.26;
  private static final double DRIVE_kI = 0;
  private static final double DRIVE_kD = 0;
  private static final double DESIRED_TARGET_AREA = 13.0;
  private static final double DESIRED_TARGET_STEER_ANGLE = 0;
  private static final double MAX_DRIVE = 0.7;
  
  private static double drive_error, prev_drive_error = 0, i_drive_error, d_drive_error;
  private static double steer_error, prev_steer_error = 0, i_steer_error, d_steer_error;
  private static final int MOVING_AVG_SIZE = 4;
  private static MovingAverage drive_moving_avg_list = new MovingAverage(MOVING_AVG_SIZE);
  private static MovingAverage steer_moving_avg_list = new MovingAverage(MOVING_AVG_SIZE);

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
    // boolean tv = Robot.m_limelight_camera_subsystem.Find_Target();
    double tx = Robot.m_limelight_camera_subsystem.Get_X();
    // double ty = Robot.m_limelight_camera_subsystem.Get_Y();
    double ta = Robot.m_limelight_camera_subsystem.Get_Area();

    /* PID for driving to HP Target */
    drive_moving_avg_list.addToMovingAverage(ta);
    steer_moving_avg_list.addToMovingAverage(tx);

    double drive_moving_average = drive_moving_avg_list.getMovingAverage();
    double steer_moving_average = steer_moving_avg_list.getMovingAverage();

    // Start with PID steering //
    steer_error = (DESIRED_TARGET_STEER_ANGLE - steer_moving_average) / 27; // Scale +-27 deg FOV to +-1 for steering command
    d_steer_error = steer_error - prev_steer_error;
    i_steer_error += steer_error;
    double steer_cmd = (STEER_kP * steer_error) + (STEER_kI * i_steer_error) + (STEER_kD * d_steer_error);

    
    // try to drive forward using PID until the target area reaches our desired area //
    drive_error = DESIRED_TARGET_AREA - drive_moving_average;
    d_drive_error = drive_error - prev_drive_error;
    i_drive_error += drive_error;
    double drive_cmd = (DRIVE_kP * drive_error) + (DRIVE_kI * i_drive_error) + (DRIVE_kD * d_drive_error);

    prev_drive_error = drive_error;
    prev_steer_error = steer_error;

    // don't let the robot drive too fast into the goal
    // if (drive_cmd > MAX_DRIVE) {
    //   drive_cmd = MAX_DRIVE;
    // }

    Robot.m_drivetrain_subsystem.ArcadeDrive(drive_cmd, steer_cmd);
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
