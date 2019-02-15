/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.SpeedControllerGroup;      // A WPI library used for grouping motors for synchronized 
import edu.wpi.first.wpilibj.Joystick;

import com.ctre.phoenix.motorcontrol.ControlMode;       // A CTRE library used for control mode selection in the CTRE library
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;  // A CTRE library used for TalonSRX control using CAN protocol

import frc.robot.RobotMap;
import frc.robot.commands.Cargo_Stop_Command;


/**
 * Add your docs here.
 */
public class Cargo_Subsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private final WPI_TalonSRX Cargo = new WPI_TalonSRX(RobotMap.TALONSRX_CARGO);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new Cargo_Stop_Command());
  }
  public void MoveCargoUp(){
    Cargo.set(1);
  }

  public void MoveCargoDown(){
    Cargo.set(-1);
  }

  public void Stop(){
    Cargo.set(0);
  }

}

