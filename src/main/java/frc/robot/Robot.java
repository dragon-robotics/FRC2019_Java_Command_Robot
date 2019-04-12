/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSource.ConnectionStrategy;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTableEntry;
// Commands //
import frc.robot.commands.Teleop_Drive;
import frc.robot.commands.Auto_Drive;

// Subsystems //
import frc.robot.subsystems.DriveTrain_Subsystem;
import frc.robot.subsystems.Elevator_Subsystem;
import frc.robot.subsystems.Cargo_Subsystem;
import frc.robot.subsystems.HatchPanel_Subsystem;
import frc.robot.subsystems.Compressor_Subsystem;
import frc.robot.subsystems.Limelight_Camera_Subsystem;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static DriveTrain_Subsystem m_drivetrain_subsystem;
  public static Cargo_Subsystem m_cargo_subsystem;
  public static HatchPanel_Subsystem m_hatch_panel_subsystem;
  public static Compressor_Subsystem p_compressor_subsystem;
  public static Elevator_Subsystem m_elevator_subsystem;
  public static Limelight_Camera_Subsystem m_limelight_camera_subsystem;
  public static OI m_oi;

  /* Shuffleboard Tabs + Widgets */
  public static ShuffleboardTab test_tab = Shuffleboard.getTab("Test");
  public static NetworkTableEntry direction_nt = test_tab.add("Direction", 1).getEntry();
  public static NetworkTableEntry steer_speed_nt = test_tab.add("Steer Speed", 1).getEntry();

  public static NetworkTableEntry steer_p_nt = test_tab.add("Steer kP", 1).getEntry();
  public static NetworkTableEntry steer_i_nt = test_tab.add("Steer kI", 1).getEntry();
  public static NetworkTableEntry steer_d_nt = test_tab.add("Steer kD", 1).getEntry();

  public static NetworkTableEntry drive_p_nt = test_tab.add("Drive kP", 1).getEntry();
  public static NetworkTableEntry drive_i_nt = test_tab.add("Drive kI", 1).getEntry();
  public static NetworkTableEntry drive_d_nt = test_tab.add("Drive kD", 1).getEntry();

  public static NetworkTableEntry desired_target_area_nt = test_tab.add("Desired Target Area", 1).getEntry();
  public static NetworkTableEntry desired_target_steer_angle_nt = test_tab.add("Desired Target Steer Angle", 1).getEntry();
  public static NetworkTableEntry max_drive_nt = test_tab.add("Max Drive", 1).getEntry();
  public static NetworkTableEntry steer_error_threshold_nt = test_tab.add("Steer Error Threshold", 1).getEntry();

  public static ShuffleboardTab auto_drive_tab = Shuffleboard.getTab("Auto Drive");
  public static NetworkTableEntry forward_time_nt = test_tab.add("Forward Time", 1).getEntry();
  public static NetworkTableEntry forward_speed_nt = test_tab.add("Forward Speed", 1).getEntry();
  public static NetworkTableEntry lvl2_forward_time_nt = test_tab.add("Level 2 Foward Time", 1).getEntry();
  public static NetworkTableEntry lvl2_forward_speed_nt = test_tab.add("Level 2 Forward Speed", 1).getEntry();

  public static NetworkTableEntry drive_time_nt = test_tab.add("Drive Time", 1).getEntry();
  public static NetworkTableEntry drive_amount_nt = test_tab.add("Drive Amount", 1).getEntry();
  public static NetworkTableEntry drive_path_steering_time_nt = test_tab.add("Drive Path Steering Time", 1).getEntry();
  public static NetworkTableEntry drive_path_steer_amount_nt = test_tab.add("Drive Path Steer Amount", 1).getEntry();

  public static NetworkTableEntry find_target_steer_amount_nt = test_tab.add("Find Target Steer Amount", 1).getEntry();
  public static NetworkTableEntry backward_time_nt = test_tab.add("Backward Time", 1).getEntry();
  public static NetworkTableEntry backward_speed_nt = test_tab.add("Backward Speed", 1).getEntry();

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  public Robot() {
    super(0.01); // Set period to 10ms to achieve 100hz update rate
  }

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    m_drivetrain_subsystem = new DriveTrain_Subsystem();
    m_cargo_subsystem = new Cargo_Subsystem();
    m_hatch_panel_subsystem = new HatchPanel_Subsystem();
    p_compressor_subsystem = new Compressor_Subsystem();
    m_elevator_subsystem = new Elevator_Subsystem();
    m_limelight_camera_subsystem = new Limelight_Camera_Subsystem();
    m_oi = new OI();
    m_chooser.setDefaultOption("Default Auto", new Teleop_Drive());
    // chooser.addOption("My Auto", new MyAutoCommand());
    SmartDashboard.putData("Auto mode", m_chooser);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like diagnostics that you want ran during disabled, autonomous,
   * teleoperated and test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This function is called once each time the robot enters Disabled mode. You
   * can use it to reset any subsystem information you want to clear when the
   * robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable chooser
   * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
   * remove all of the chooser code and uncomment the getString code to get the
   * auto name from the text box below the Gyro
   *
   * <p>
   * You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons to
   * the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    // m_autonomousCommand = m_chooser.getSelected();
    String autoSelected = SmartDashboard.getString("Auto Selector", "Right Side Auto Lvl2");

    switch (autoSelected) {
    case "Right Side Auto Lvl2":
      m_autonomousCommand = new Auto_Drive(RobotMap.LEVEL_TWO, RobotMap.RIGHT);
      break;
    case "Left Side Auto Lvl2":
      m_autonomousCommand = new Auto_Drive(RobotMap.LEVEL_TWO, RobotMap.LEFT);
      break;
    case "Right Side Auto Lvl1":
      m_autonomousCommand = new Auto_Drive(RobotMap.LEVEL_ONE, RobotMap.RIGHT);
      break;
    case "Left Side Auto Lvl1":
      m_autonomousCommand = new Auto_Drive(RobotMap.LEVEL_ONE, RobotMap.LEFT);
      break;
    default:
      m_autonomousCommand = new Teleop_Drive();
      break;
    }

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}