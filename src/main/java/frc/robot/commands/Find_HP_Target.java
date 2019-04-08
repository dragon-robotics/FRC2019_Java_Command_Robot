/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class Find_HP_Target extends Command {

  private int direction;
  private double steer_speed;

  private ShuffleboardTab tab = Shuffleboard.getTab("Test");
  private NetworkTableEntry direction_nt = tab.add("Direction", 1).getEntry();
  private NetworkTableEntry steer_speed_nt = tab.add("Steer Speed", 1).getEntry();

  /* This is for testing input */
  public Find_HP_Target(){
    direction = (int)direction_nt.getDouble(RobotMap.LEFT); // Defaults to left
    steer_speed = steer_speed_nt.getDouble(0.5);            // Defaults to half-speed for turning
  }

  /* This is using tuned input */
  public Find_HP_Target(int direction, double steer_speed) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_limelight_camera_subsystem);
    requires(Robot.m_drivetrain_subsystem);

    this.direction = direction;
    this.steer_speed = steer_speed;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    /* Rotate the robot until target is valid depending on the direction */
    if(direction < 1){
      Robot.m_drivetrain_subsystem.ArcadeDrive(0, direction);
    }
    else{
      Robot.m_drivetrain_subsystem.ArcadeDrive(0, -direction);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(Robot.m_limelight_camera_subsystem.Find_Target()){
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
