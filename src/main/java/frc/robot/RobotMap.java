/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  
  /* Left Motor */
  public static final int MOTOR_LEFTFRONT = 2;
  public static final int MOTOR_LEFTREAR = 0;

  /* Right Motor */
  public static final int MOTOR_RIGHTFRONT = 3;
  public static final int MOTOR_RIGHTREAR = 1;

  /* TalonSRX */
  public static final int TALONSRX_CARGO = 1;
  public static final int TALONSRX_HATCH = 2;

  /* Joystick port */
  public static final int  J_STICK_DRIVER = 0;
  public static final int  J_STICK_CONTROL = 1;

  /* Joystick */
  public static final int BUTTON_RIGHT = 5; 
  public static final int BUTTON_LEFT = 6;
  public static final int BUTTON_X = 3; 
  public static final int BUTTON_Y = 4;
  public static final int BUTTON_A = 1; 
  public static final int BUTTON_B = 2;
  public static final int BUTTON_BACK = 7;
  public static final int BUTTON_START = 8;
  public static final int BUTTON_LEFTSTICK = 9;
  public static final int BUTTON_RIGHTSTICK = 10;

  /* Pneumatics */
  public static final int CAN_COMPRESSOR = 0;
  public static final int CAN_DOUBLESOLENOID_1_LEFT = 0;
  public static final int CAN_DOUBLESOLENOID_1_RIGHT = 1;
  public static final int CAN_DOUBLESOLENOID_2_LEFT = 2;
  public static final int CAN_DOUBLESOLENOID_2_RIGHT = 3;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static final int rangefinderPort = 1;
  // public static final int rangefinderModule = 1;  
}
