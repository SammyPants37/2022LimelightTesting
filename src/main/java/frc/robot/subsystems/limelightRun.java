package frc.robot.subsystems;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class limelightRun extends SubsystemBase{
    
    private static XboxController controller = new XboxController(0);

    public static void aim() {
        if (controller.getAButton()) {
            if (RobotContainer.x < 0) {
                DriveTrain.arcadeDrive(0.0, -0.5);
            } else {
                DriveTrain.arcadeDrive(0.0, 0.5);
            }
        }
    }
}
