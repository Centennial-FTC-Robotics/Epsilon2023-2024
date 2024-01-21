package Epsilon.Subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import Epsilon.Subsystem;
import com.qualcomm.robotcore.hardware.Gamepad;

@Config
public class Intake implements Subsystem {

    public static double intakeUp = 0.5;
    public static double intakeDown = 0.5;

    public Servo leftServo;
    public DcMotor wheelMotor;
    public CRServo boxWheelServo;
    public Intake(final HardwareMap hMap) {
//        leftServo = hMap.get(Servo.class, "left");
        wheelMotor = hMap.get(DcMotor.class, "wheel");
    }

    public void spinWheel(double power) {
        wheelMotor.setPower(-power);
    }

    public void lowerIntake() {
//        leftServo.setPosition(intakeDown);
    }

    public void fullLowerIntake() {
//        leftServo.setPosition(intakeDown);
    }

    public void raiseIntake() {
//        leftServo.setPosition(intakeUp);
    }

    public void teleOpUpdate(Gamepad gamepad1, Gamepad gamepad2) {

        spinWheel(gamepad2.right_trigger - gamepad2.left_trigger);

        if (gamepad2.dpad_up) raiseIntake();
        if (gamepad2.dpad_down) fullLowerIntake();
    }
}