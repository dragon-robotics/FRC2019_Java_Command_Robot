/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;                // A WPI library used for controlling and obtaining the status of the compressor

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.Compressor_On;

/**
 * Add your docs here.
 */
public class Compressor_Subsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private final Compressor compressor = new Compressor(0);  // Compressor code

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new Compressor_On());
  }

  public void Compressor_Start(){
    compressor.setClosedLoopControl(true);
  }

  public void Compressor_Stop(){
    compressor.setClosedLoopControl(false);
  }
}
