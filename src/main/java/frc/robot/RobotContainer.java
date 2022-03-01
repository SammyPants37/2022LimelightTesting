// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.limelightRun;
import frc.robot.subsystems.otherMotors;
import frc.robot.subsystems.phematics;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  public static Timer timer = new Timer();
  public static int stage = 0;
  public XboxController controller = new XboxController(0);

  static NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  static NetworkTableEntry tx = table.getEntry("tx");
  static NetworkTableEntry ty = table.getEntry("ty");
  static NetworkTableEntry ta = table.getEntry("ta");

  //read limelight periodically
  public static double x = tx.getDouble(0.0);
  public static double y = ty.getDouble(0.0);
  public static double area = ta.getDouble(0.0);

  public limelightRun lRun = new limelightRun();
  public otherMotors otherMs = new otherMotors();
  public phematics pnematic = new phematics();

  final JoystickButton buttonX = new JoystickButton(controller, Constants.IOConstants.xButtonChannel);
  final JoystickButton shooterButton = new JoystickButton(controller,
  Constants.IOConstants.leftBumperChannel);
  final JoystickButton climberUp = new JoystickButton(controller, Constants.IOConstants.aButtonChannel);
  final JoystickButton climberDown = new JoystickButton(controller,
  Constants.IOConstants.bButtonChannel);
  final JoystickButton toggleClimberpnematics = new JoystickButton(controller,
  Constants.IOConstants.startButtonChannel);
  final JoystickButton toggleCollectorpnematics = new JoystickButton(controller,
  Constants.IOConstants.backButtonChannel);

  //////////////
  // COMMANDS //
  //////////////

  StartEndCommand turnWithLimelight = new StartEndCommand(
    () -> limelightRun.aim(),
    () -> limelightRun.stop(),
  lRun);
  
  StartEndCommand runShooter = new StartEndCommand(
    () -> otherMs.runShooter(Constants.shooterSpeed),
    () -> otherMs.stopShooter(),
    otherMs);

  StartEndCommand climbClimber = new StartEndCommand(
    () -> otherMs.runClimber(Constants.climberSpeed),
    () -> otherMs.stopClimber(),
  otherMs);

  StartEndCommand lowerClimber = new StartEndCommand(
    () -> otherMs.runClimber(-Constants.climberSpeed),
    () -> otherMs.stopClimber(),
  otherMs);
  
  InstantCommand toggleCollector = new InstantCommand(
    () -> pnematic.toggleCollector(),
  pnematic);
  
  InstantCommand toggleClimber = new InstantCommand(
    () -> pnematic.toggleClimber(),
    pnematic);

  //////////////////////
  // no more commands //
  //////////////////////

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  private void configureButtonBindings() {
    buttonX.whileHeld(turnWithLimelight);
    shooterButton.whileHeld(runShooter);
    climberUp.whileHeld(climbClimber);
    climberDown.whileHeld(lowerClimber);
    toggleCollectorpnematics.whenPressed(toggleCollector);
    toggleClimberpnematics.whenPressed(toggleClimber);
  }

  public void doAton() {
    //simple aton code
    if (timer.get() < 1.0 & stage == 0) {
      DriveTrain.arcadeDrive(0.7, 0.0);
    } else if (timer.get() < 1.0 & stage == 1) {
      DriveTrain.arcadeDrive(0.0, 0.7);
    } else if (timer.get() < 1.0 & stage == 2) {
      DriveTrain.arcadeDrive(0.7, 0.0);
    } else {
      DriveTrain.stop(); // stop robot
      stage += 1;
      timer.reset();
    }
  }
}
