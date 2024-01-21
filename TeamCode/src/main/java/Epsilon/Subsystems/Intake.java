package Epsilon.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import Epsilon.Subsystem;
import com.qualcomm.robotcore.hardware.Gamepad;

public class Intake implements Subsystem {
    public Servo leftServo;
    public DcMotor wheelMotor;
    public CRServo boxWheelServo;
    public Intake(final HardwareMap hMap) {
        leftServo = hMap.get(Servo.class, "left");
        wheelMotor = hMap.get(DcMotor.class, "wheel");
        boxWheelServo = hMap.get(CRServo.class, "boxWheel");
    }

    public void spinWheel(double power) {
        wheelMotor.setPower(power);
        boxWheelServo.setPower(power);
    }

    public void lowerIntake() {
        leftServo.setPosition(0.47);
    }

    public void fullLowerIntake() {
        leftServo.setPosition(0.5);
    }

    public void raiseIntake() {
        leftServo.setPosition(0.0);
    }

    public void teleOpUpdate(Gamepad gamepad1, Gamepad gamepad2) {
        if (gamepad2.b) spinWheel(-1);
        else if (gamepad2.x) spinWheel( 0.5);
        else spinWheel(0);

        if (gamepad2.dpad_up) raiseIntake();
        if (gamepad2.dpad_down) fullLowerIntake();
    }
}