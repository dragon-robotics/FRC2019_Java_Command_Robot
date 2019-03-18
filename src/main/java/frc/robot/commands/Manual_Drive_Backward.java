/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.Robot;

public class Manual_Drive_Backward extends TimedCommand {

  private double moveSpeed;

  public Manual_Drive_Backward() {
    super(0.5); // Set timeout of this command to be 0.5 second
    
    moveSpeed = -0.3;  // By default, the moveSpeed of the CIM will be -0.3
    
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_drivetrain_subsystem);
  }

  public Manual_Drive_Backward(double time, double moveSpeed) {
    super(time);

    requires(Robot.m_drivetrain_subsystem);

    this.moveSpeed = moveSpeed;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.m_drivetrain_subsystem.TankDrive(moveSpeed, moveSpeed);
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
