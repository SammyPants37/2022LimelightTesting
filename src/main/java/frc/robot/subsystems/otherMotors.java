package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class otherMotors extends SubsystemBase{
    
    public static WPI_TalonFX shooter = new WPI_TalonFX(Constants.motorConstants.shooterMotorPort);
    public static WPI_TalonFX LClimber = new WPI_TalonFX(Constants.motorConstants.leftClimberMotorPort);
    public static WPI_TalonFX RClimber = new WPI_TalonFX(Constants.motorConstants.rightClimberMotorPort);

    public static CANSparkMax collector = new CANSparkMax(Constants.motorConstants.collectorMotorPort,
    MotorType.kBrushless);
    public static CANSparkMax conveyor = new CANSparkMax(Constants.motorConstants.conveyorMotorPort,
    MotorType.kBrushless);

    public otherMotors() {
        conveyor.restoreFactoryDefaults();
        conveyor.setIdleMode(IdleMode.kBrake);
        collector.restoreFactoryDefaults();
        collector.setIdleMode(IdleMode.kBrake);
    }

    public void runShooter(double speed) {
        shooter.set(speed);
    }

    public void stopShooter() {
        shooter.stopMotor();
    }

    public void runCollector(double speed) {
        collector.set(speed);
    }

    public void stopcollector() {
        collector.stopMotor();
    }

    public void runConveyor(double speed) {
        conveyor.set(speed);
    }

    public void stopconveyor() {
        conveyor.stopMotor();
    }

    public void runClimber(double speed) {
        LClimber.set(speed);
        RClimber.set(speed);
    }

    public void stopClimber() {
        LClimber.stopMotor();
        RClimber.stopMotor();
    }
}
