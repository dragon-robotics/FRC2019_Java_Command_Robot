/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Deploy_Cargo extends CommandGroup {
  /**
   * Add your docs here.
   */
  public Deploy_Cargo() {
   //ROCKET
    /*addSequential(new)
    move elevator up 3 secs
    shoot cargo 
    move elevator down 3 secs*/
    double driveBackwardTime = 0.2;
    double driveBackwardSpeed = -0.5;  
    
    double CargoupTime = 0.05;
    double CargoupSpeed = 0.5;

    double CargodownTime = 0.05;
    double CargodownSpeed = 0.5;

    double CargooutTime = 0.2;
    double CargooutSpeed = 0.5;

    double CargoinTime = 0.2;
    double CargoinSpeed = 0.5;

    addSequential(new Cargo_Shoot(CargooutTime, CargooutSpeed));
    addSequential(new Cargo_Intake(CargoinTime, CargoinSpeed));
  }
}
