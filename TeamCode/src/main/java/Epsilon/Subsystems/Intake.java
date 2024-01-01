package Epsilon.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import Epsilon.Subsystem;
import com.qualcomm.robotcore.hardware.Gamepad;

public class Intake implements Subsystem {
    public Servo leftServo;
    public Servo rightServo;
    public DcMotor wheelMotor;
    public Intake(final HardwareMap hMap) {
        leftServo = hMap.get(Servo.class, "left");
        rightServo = hMap.get(Servo.class, "right");
        wheelMotor = hMap.get(DcMotor.class, "wheel");
    }

    public void spinWheel(double power) {
        wheelMotor.setPower(power);
    }

    public void lowerIntake() {
        leftServo.setPosition(0.5);
        rightServo.setPosition(0);
    }

    public void raiseIntake() {
        leftServo.setPosition(0);
        rightServo.setPosition(0.5);
    }



    public void teleOpUpdate(Gamepad gamepad1, Gamepad gamepad2) {
        if (gamepad2.b) spinWheel(-0.8); //does this shit do anything
        if (gamepad2.x) spinWheel( 0.5); //does this shit do anything
        else spinWheel(0);

        if (gamepad2.dpad_up) raiseIntake();
        if (gamepad2.dpad_down) lowerIntake();
    }
}