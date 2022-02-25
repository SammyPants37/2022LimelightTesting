package frc.robot.subsystems;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class doMotors extends SubsystemBase{
private static XboxController controller = new XboxController(0);
  private static double speed = 0;
  private static double rot = 0;

  public static void driveMotors() {
    if (controller.getRawAxis(Constants.rotChannel) >= Constants.joyMin |
        controller.getRawAxis(Constants.rotChannel) <= -Constants.joyMin) {
      rot = controller.getRawAxis(Constants.rotChannel);
    } else {
      rot = 0;
    }
    // speed setting
    if (controller.getRawAxis(Constants.speedChannel) >= Constants.joyMin |
        controller.getRawAxis(Constants.speedChannel) <= -Constants.joyMin) {
      speed = controller.getRawAxis(Constants.speedChannel);
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
}
