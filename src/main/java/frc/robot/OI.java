/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;    // A WPI library used for joystick buttons mapping

import frc.robot.commands.Cargo_Up_Command;
import frc.robot.commands.Cargo_Down_Command;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // public Joystick j_stick_driver = new Joystick(RobotMap.J_STICK_DRIVER);
  // public Joystick j_stick_control = new Joystick(RobotMap.J_STICK_CONTROL);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());

    /* Robot Driver - AKA Driver 1 */
    public final Joystick j_stick_driver = new Joystick(0);  // Drive joystick initialization
    public final JoystickButton j_stick_driver_LB = new JoystickButton(j_stick_driver, 5);
    public final JoystickButton j_stick_driver_RB = new JoystickButton(j_stick_driver, 6);           // Right button 
    public final JoystickButton j_stick_driver_X = new JoystickButton(j_stick_driver, 3);            // X button
    public final JoystickButton j_stick_driver_Y = new JoystickButton(j_stick_driver, 4);            // Y button
    public final JoystickButton j_stick_driver_A = new JoystickButton(j_stick_driver, 1);            // A button
    public final JoystickButton j_stick_driver_B = new JoystickButton(j_stick_driver, 2);            // B button
    public final JoystickButton j_stick_driver_Back = new JoystickButton(j_stick_driver, 7);         // Back button
    public final JoystickButton j_stick_driver_Start = new JoystickButton(j_stick_driver, 8);        // Start button
    public final JoystickButton j_stick_driver_leftStick = new JoystickButton(j_stick_driver, 9);    // Left-Stick button
    public final JoystickButton j_stick_driver_rightStick = new JoystickButton(j_stick_driver, 10);  // Right-Stick button
  
    /* Robot Controller - AKA Driver 2 */
    public final Joystick j_stick_control = new Joystick(1);  // Control joystick initialization
    public final JoystickButton j_stick_control_LB = new JoystickButton(j_stick_control, 5);           // Left button
    public final JoystickButton j_stick_control_RB = new JoystickButton(j_stick_control, 6);           // Right button 
    public final JoystickButton j_stick_control_X = new JoystickButton(j_stick_control, 3);            // X button
    public final JoystickButton j_stick_control_Y = new JoystickButton(j_stick_control, 4);            // Y button
    public final JoystickButton j_stick_control_A = new JoystickButton(j_stick_control, 1);            // A button
    public final JoystickButton j_stick_control_B = new JoystickButton(j_stick_control, 2);            // B button
    public final JoystickButton j_stick_control_Back = new JoystickButton(j_stick_control, 7);         // Back button
    public final JoystickButton j_stick_control_Start = new JoystickButton(j_stick_control, 8);        // Start button
    public final JoystickButton j_stick_control_leftStick = new JoystickButton(j_stick_control, 9);    // Left-Stick button
    public final JoystickButton j_stick_control_rightStick = new JoystickButton(j_stick_control, 10);  // Right-Stick button

    public OI(){
      j_stick_control_X.whileHeld(new Cargo_Up_Command());
      j_stick_control_Y.whileHeld(new Cargo_Down_Command());
    }
}
