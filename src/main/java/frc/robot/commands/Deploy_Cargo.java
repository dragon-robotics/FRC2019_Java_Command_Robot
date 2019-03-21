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
    
    double ElevatorUpTime = 3;
    double ElevatorUpSpeed = 1;

    double ElevatorDownTime = 3;
    double ElevatorDownSpeed = 1;

    double CargoShootTime = 0.2;
    double CargoShootSpeed = 1;

    addSequential(new Elevator_Up(ElevatorUpTime, ElevatorUpSpeed));
    addSequential(new Cargo_Shoot(CargoShootTime, CargoShootSpeed));
    addSequential(new Elevator_Down(ElevatorDownTime, ElevatorDownSpeed));
  }
}
