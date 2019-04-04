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
  public int Find_Target(){
    int tv = (int)NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
    return tv;
  }

  public double Get_X(){
    double tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
    return tx;
  }

  public double Get_Y() {
    double ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
    return ty;
  }

  public double Get_Area() {
    double ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);
    return ta;
  }

  public double Get_Skew(){
    double ts = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ts").getDouble(0);
    return ts;
  }

  public double Get_Pipeline_Latency(){
    double tl = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tl").getDouble(0);
    return tl;
  }

  public double Get_Sidelength_Of_Shortest_Side_Of_Fitted_Bounding_Box(){
    double tshort = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tshort").getDouble(0);
    return tshort;
  }

  public double Get_Sidelength_Of_Longest_Side_Of_Fitted_Bounding_Box() {
    double tlong = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tlong").getDouble(0);
    return tlong;
  }

  public double Get_Horizontal_Sidelength_Of_Rough_Bounding_Box() {
    double thor = NetworkTableInstance.getDefault().getTable("limelight").getEntry("thor").getDouble(0);
    return thor;
  }

  public double Get_Vertical_Sidelength_Of_Rough_Bounding_Box() {
    double tvert = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tvert").getDouble(0);
    return tvert;
  }

  public double Get_Pipeline(){
    double getpipe = NetworkTableInstance.getDefault().getTable("limelight").getEntry("getpipe").getDouble(0);
    return getpipe;
  }

  /* Camera Controls */
  public void Set_LED(int led_value){
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("<variablename>").setNumber(led_value);
  }

  public void Set_Cam_Mode(int cam_mode) {
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("<variablename>").setNumber(cam_mode);
  }

  public void Set_Pipeline(int pipeline) {
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("<variablename>").setNumber(pipeline);
  }

  public void Set_Snapshot(int snapshot) {
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("<variablename>").setNumber(snapshot);
  }

  /* Corners */

  /* Raw Targets */

  /* Raw CrossHair */
}
