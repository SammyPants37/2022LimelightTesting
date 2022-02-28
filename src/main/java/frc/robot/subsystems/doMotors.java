package frc.robot.subsystems;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class doMotors extends SubsystemBase{
  private static XboxController controller = new XboxController(0);
  private static otherMotors motors = new otherMotors();
  private static double speed = 0;
  private static double rot = 0;
  private static double collectorSpeed = 0;

  public static void driveMotors() {
    if (controller.getRawAxis(Constants.IOConstants.rightXAxisChannel) >= Constants.joyMin |
        controller.getRawAxis(Constants.IOConstants.rightXAxisChannel) <= -Constants.joyMin) {
      rot = controller.getRawAxis(Constants.IOConstants.rightXAxisChannel);
    } else {
      rot = 0;
    }
    // speed setting
    if (controller.getRawAxis(Constants.IOConstants.leftYAxisChannel) >= Constants.joyMin |
        controller.getRawAxis(Constants.IOConstants.leftYAxisChannel) <= -Constants.joyMin) {
      speed = controller.getRawAxis(Constants.IOConstants.leftYAxisChannel);
    } else {
      speed = 0;
    }
    // speed maxing
    if (speed >= Constants.speedMax) {
      speed = Constants.speedMax;
    } else if (speed <= -Constants.speedMax) {
      speed = -Constants.speedMax;
    }
    // rot maxing
    if (rot >= Constants.speedMax) {
      rot = Constants.speedMax;
    } else if (rot <= -Constants.speedMax) {
      rot = -Constants.speedMax;
    }
    DriveTrain.arcadeDrive(-speed, rot);
  }

  public static void otherMotors() {
    if (controller.getRawAxis(Constants.IOConstants.rightTriggerChannel) >= Constants.joyMin |
        controller.getRawAxis(Constants.IOConstants.rightTriggerChannel) <= -Constants.joyMin) {
      collectorSpeed = controller.getRawAxis(Constants.IOConstants.rightTriggerChannel);
    } else {
      collectorSpeed = 0;
    }
    // speed maxing
    if (collectorSpeed >= Constants.speedMax) {
      collectorSpeed = Constants.speedMax;
    }
    if (controller.getRawButton(Constants.IOConstants.rightBumperChannel)) {
      collectorSpeed = -collectorSpeed;
    }
    motors.runCollector(collectorSpeed);
    motors.runConveyor(collectorSpeed);

    if (controller.getRawButton(Constants.IOConstants.leftBumperChannel)) {
      motors.runShooter(Constants.shooterSpeed);
    }

    if (controller.getRawButton(Constants.IOConstants.aButtonChannel)) {
      motors.runClimber(Constants.climberSpeed);
    } else if (controller.getRawButton(Constants.IOConstants.bButtonChannel)) {
      motors.runClimber(-Constants.climberSpeed);
    }
  }
}
