package Epsilon.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import Epsilon.Subsystem;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

public class Outtake implements Subsystem {
    public Servo slideServo1;
    public Servo dumperServo;
    public Outtake(final HardwareMap hMap) {
        slideServo1 = hMap.get(Servo.class, "slideServo1");
        dumperServo = hMap.get(Servo.class, "dumperServo");
    }

    public void raiseSlides() {
        slideServo1.setPosition(0.5);
    }

    public void lowerSlides() {
        slideServo1.setPosition(0);
    }

    public void emptyBox() {
        dumperServo.setPosition(0.9);
    }

    public void returnBox() {
        dumperServo.setPosition(0.2);
    }

    public void extendOuttake(){
        raiseSlides();
        emptyBox();
    }

    public void retractOuttake(){
        returnBox();
        lowerSlides();
    }

    public void teleOpUpdate(Gamepad gamepad1, Gamepad gamepad2) {
        if (gamepad2.dpad_up) extendOuttake();
        if (gamepad2.dpad_down) retractOuttake();
    }
}