package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class limelightRun extends SubsystemBase{

    public static void aim() {
        if (RobotContainer.x < 0) {
            DriveTrain.arcadeDrive(0.0, -0.5);
        } else {
            DriveTrain.arcadeDrive(0.0, 0.5);
        }   
    }

    public static void stop() {
        DriveTrain.stop();
    }
}
