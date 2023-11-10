package Epsilon.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Intake extends SubsystemBase {
    public Servo leftServo;
    public Servo rightServo;
    public DcMotor wheelMotor;
    public Intake(final HardwareMap hMap, final String name) {
        leftServo = hMap.get(Servo.class, name);
        rightServo = hMap.get(Servo.class, name);
        wheelMotor = hMap.get(DcMotor.class, name);
    }

    public void spinWheel(double power) {
        wheelMotor.setPower(power);
    }
}
