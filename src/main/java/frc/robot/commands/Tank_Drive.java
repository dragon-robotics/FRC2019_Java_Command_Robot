/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class Tank_Drive extends Command {
  public Tank_Drive() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_drivetrain_subsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double leftJoyY = -Robot.m_oi.j_stick_driver.getRawAxis(RobotMap.AXIS_LEFT_Y);
    double rightJoyY = Robot.m_oi.j_stick_driver.getRawAxis(RobotMap.AXIS_LEFT_X);
    boolean LB_Pressed = Robot.m_oi.j_stick_control_LB.get();
    leftJoyY = LB_Pressed ? leftJoyY / 2 : leftJoyY;
    rightJoyY = LB_Pressed ? rightJoyY / 2 : rightJoyY;
    Robot.m_drivetrain_subsystem.TankDrive(leftJoyY, rightJoyY);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
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
