/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import frc.robot.RobotMap;
import frc.robot.commands.ArcadeDrive;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class DriveTrain_Subsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  /* Left Motors */
  private final PWMVictorSPX m_leftFront = new PWMVictorSPX(RobotMap.MOTOR_LEFTFRONT);                         // Left Front Motor using PWM Victor
  private final PWMVictorSPX m_leftRear = new PWMVictorSPX(RobotMap.MOTOR_LEFTREAR);                          // Left Rear Motor using PWM Victor
  SpeedControllerGroup m_left = new SpeedControllerGroup(m_leftFront, m_leftRear);      // Left Front + Left Rear synchronized control
  
  /* Right Motors */
  private final PWMVictorSPX m_rightFront = new PWMVictorSPX(RobotMap.MOTOR_RIGHTFRONT);                        // Right Front Motor using PWM Victor
  private final PWMVictorSPX m_rightRear = new PWMVictorSPX(RobotMap.MOTOR_RIGHTREAR);                         // Right Rear Motor using PWM Victor
  SpeedControllerGroup m_right = new SpeedControllerGroup(m_rightFront, m_rightRear);   // Right Front + Right Rear synchronized control
  
  private DifferentialDrive m_robotDrive = new DifferentialDrive(m_left,m_right);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new ArcadeDrive());
  }

  public void TeleopDrive(double leftJoyY, double rightJoyX){
    m_robotDrive.arcadeDrive(leftJoyY, rightJoyX);
  }

  public void Stop(){
    m_robotDrive.arcadeDrive(0, 0); // Stops the robot
  }
}
