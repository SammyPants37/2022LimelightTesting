// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subsystems.DriveTrain;

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

  //read values periodically
  public static double x = tx.getDouble(0.0);
  public static double y = ty.getDouble(0.0);
  public static double area = ta.getDouble(0.0);

  

  // final JoystickButton buttonname = new JoystickButton(controller, Constants.buttonchannel);

  //////////////
  // COMMANDS //
  //////////////

  // StartEndCommand name = new StartEndCommand(
  //   () -> startfunk,
  //   () -> endfunk,
  //   requirements);

  //////////////////////
  // no more commands //
  //////////////////////

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // buttonname.whileHeld(name);
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
