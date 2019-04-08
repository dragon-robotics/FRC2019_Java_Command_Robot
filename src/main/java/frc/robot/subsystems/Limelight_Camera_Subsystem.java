/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

/**
 * Add your docs here.
 */
public class Limelight_Camera_Subsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
  }

  /* To be completed */

  /* Contour Information - 1 if found, 0 is not */
  public boolean Find_Target(){
    int tv = (int)NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(2375);
    if(tv > 0){
      return true;
    }
    else{
      return false;
    }
  }

  public double Get_X(){
    double tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(2375);
    return tx;
  }

  public double Get_Y() {
    double ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(2375);
    return ty;
  }

  public double Get_Area() {
    double ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(2375);
    return ta;
  }

  public double Get_Skew(){
    double ts = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ts").getDouble(2375);
    return ts;
  }

  public double Get_Pipeline_Latency(){
    double tl = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tl").getDouble(2375);
    return tl;
  }

  public double Get_Sidelength_Of_Shortest_Side_Of_Fitted_Bounding_Box(){
    double tshort = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tshort").getDouble(2375);
    return tshort;
  }

  public double Get_Sidelength_Of_Longest_Side_Of_Fitted_Bounding_Box() {
    double tlong = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tlong").getDouble(2375);
    return tlong;
  }

  public double Get_Horizontal_Sidelength_Of_Rough_Bounding_Box() {
    double thor = NetworkTableInstance.getDefault().getTable("limelight").getEntry("thor").getDouble(2375);
    return thor;
  }

  public double Get_Vertical_Sidelength_Of_Rough_Bounding_Box() {
    double tvert = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tvert").getDouble(2375);
    return tvert;
  }

  public double Get_Pipeline(){
    double getpipe = NetworkTableInstance.getDefault().getTable("limelight").getEntry("getpipe").getDouble(2375);
    return getpipe;
  }

  /* Camera Controls */

  /*
   * Sets limelight’s LED state
   * 0 - use the LED Mode set in the current pipeline
   * 1 - force off
   * 2 - force blink
   * 3 - force on
   */
  public void Set_LED(int led_value){
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(led_value);
  }

  /*
   * Sets limelight’s operation mode
   * 0 - Vision Processor
   * 1 - Driver Camera (Increases Exposure, disables vision processing)
   */
  public void Set_Cam_Mode(int cam_mode) {
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("camMode").setNumber(cam_mode);
  }

  /*
   * Sets limelight’s current pipeline
   * 0..9 - Select pipeline 0..9
   */
  public void Set_Pipeline(int pipeline) {
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(pipeline);
  }

  /*
   * Sets limelight’s streaming mode
   * 0 - Standard - Side-by-side streams if a webcam is attached to Limelight 
   * 1 - PiP Main - The secondary camera stream is placed in the lower-right corner of the primary camera stream
   * 2 - PiP Secondary - The primary camera stream is placed in the lower-right corner of the secondary camera stream
   */
  public void Set_Stream(int stream){
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("stream").setNumber(stream);
  }

  /*
   * Allows users to take snapshots during a match
   * 0 - Stop taking snapshots
   * 1 - Take two snapshots per second
   */
  public void Set_Snapshot(int snapshot) {
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("snapshot").setNumber(snapshot);
  }

  /* Corners */

  /* Raw Targets */

  /* Raw CrossHair */
}
