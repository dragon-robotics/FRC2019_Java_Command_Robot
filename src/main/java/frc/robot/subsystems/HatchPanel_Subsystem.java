/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import edu.wpi.first.wpilibj.DoubleSolenoid;            // A WPI library used for controlling and obtaining the status of the double solenoid
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;      // Enumeration for double solenoid direction

import com.ctre.phoenix.motorcontrol.ControlMode;       // A CTRE library used for control mode selection in the CTRE library
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;  // A CTRE library used for TalonSRX control using CAN protocol

import frc.robot.RobotMap;
import frc.robot.commands.HatchPanel_Stop;

/**
 * Add your docs here.
 */
public class HatchPanel_Subsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private final WPI_TalonSRX HatchPanel = new WPI_TalonSRX(RobotMap.TALONSRX_HATCH);
  private final DoubleSolenoid doubleSolenoid_Hatch = new DoubleSolenoid(0,1);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new HatchPanel_Stop());
  }

  public void PistonDeploy(){
    doubleSolenoid_Hatch.set(Value.kForward); // Right solenoid go forward
  }

  public void PistonRetract(){
    doubleSolenoid_Hatch.set(Value.kReverse); // Right solenoid go forward
  }

  public void MoveForward(){
    HatchPanel.set(1);
  }

  public void MoveBackward(){
    HatchPanel.set(-1);
  }

  public void Stop(){
    HatchPanel.set(0);
    doubleSolenoid_Hatch.set(Value.kOff); // Right solenoid go forward
  }
}
