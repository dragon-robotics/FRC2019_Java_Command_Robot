/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.Robot;

public class Arcade_Drive extends TimedCommand {
  private double time;
  private double drive_speed;
  private double steer_speed;
  
  public Arcade_Drive(double time, double drive_speed, double steer_speed) {
    super(time);
    
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);

    requires(Robot.m_drivetrain_subsystem);

    this.drive_speed = drive_speed;
    this.steer_speed = steer_speed;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.m_drivetrain_subsystem.ArcadeDrive(drive_speed, steer_speed);
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
