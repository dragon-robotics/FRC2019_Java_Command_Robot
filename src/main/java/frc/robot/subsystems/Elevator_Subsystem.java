/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.ControlMode;       
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;  
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.Elevator_Move;


/**
 * Add your docs here.
 */
public class Elevator_Subsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private final WPI_TalonSRX Elevator = new WPI_TalonSRX(RobotMap.TALONSRX_ELEVATOR);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new Elevator_Move());
  }

  public void Elevator_Move(double moveSpeed){
    Elevator.set(moveSpeed);
  }

  public void Elevator_Up(){
    Elevator.set(1);
  } 
  
  public void Elevator_Down(){
    Elevator.set(-1);
  }

  public void Elevator_Stop(){
    Elevator.set(0);
  }
}
