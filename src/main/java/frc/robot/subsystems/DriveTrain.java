package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveTrain extends SubsystemBase{
    
  static Spark frontLeft = new Spark(Constants.FLM);
  static Spark rearLeft = new Spark(Constants.BLM);
  static MotorControllerGroup left = new MotorControllerGroup(frontLeft, rearLeft);

  static Spark frontRight = new Spark(Constants.FRM);
  static Spark rearRight = new Spark(Constants.BRM);
  static MotorControllerGroup right = new MotorControllerGroup(frontRight, rearRight);

  static DifferentialDrive diffDrive = new DifferentialDrive(left, right);

  public static void setInverted() {
    frontRight.setInverted(true);
    rearRight.setInverted(true);
  }

  public static void arcadeDrive(double speed, double rotation) {
    diffDrive.arcadeDrive(rotation, speed);
  }

  public static void stop() {
    diffDrive.stopMotor();
  }
}
