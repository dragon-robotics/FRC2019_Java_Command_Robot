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

public class Elevator_Move extends Command {
  public Elevator_Move() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_elevator_subsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double leftTriggerValue = Robot.m_oi.j_stick_control.getRawAxis(RobotMap.AXIS_LEFT_TRIGGER);
    double rightTriggerValue = Robot.m_oi.j_stick_control.getRawAxis(RobotMap.AXIS_RIGHT_TRIGGER);

    if(leftTriggerValue > 0 && rightTriggerValue > 0){
      // Do nothing
      Robot.m_elevator_subsystem.Elevator_Move(0);
    }
    else if(rightTriggerValue > 0){
      // Move in the positive direction based on the right trigger percentage
      Robot.m_elevator_subsystem.Elevator_Move(rightTriggerValue);
    }
    else if(leftTriggerValue > 0){
      // Move in the negative direction based on the left trigger percentage
      Robot.m_elevator_subsystem.Elevator_Move(-leftTriggerValue);
    }
    else{
      // Stop elevator motor
      Robot.m_elevator_subsystem.Elevator_Move(0);
    }
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
