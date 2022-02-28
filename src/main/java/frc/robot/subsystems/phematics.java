package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class phematics extends SubsystemBase{
    //collector
    public static final Solenoid leftIntakeSolenoid = new
    Solenoid(PneumaticsModuleType.CTREPCM,
    Constants.Pneumaticsconstants.leftIntakeSolenoidPort);
    public static final Solenoid rightIntakeSolenoid = new
    Solenoid(PneumaticsModuleType.CTREPCM,
    Constants.Pneumaticsconstants.rightIntakeSolenoidPort);
    //climber
    public static final Solenoid leftClimberSolenoid = new
    Solenoid(PneumaticsModuleType.CTREPCM,
    Constants.Pneumaticsconstants.leftClimberSolenoidPort);
    public static final Solenoid rightClimberSolenoid = new
    Solenoid(PneumaticsModuleType.CTREPCM,
    Constants.Pneumaticsconstants.rightClimberSolenoidPort);

    public void toggleClimber() {
        leftClimberSolenoid.toggle();
        rightClimberSolenoid.toggle();
    }

    public void toggleCollector() {
        leftIntakeSolenoid.toggle();
        rightIntakeSolenoid.toggle();
    }
}
