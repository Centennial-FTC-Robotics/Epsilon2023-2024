package Epsilon.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import Epsilon.Subsystem;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

public class Outtake implements Subsystem {
    public DcMotor slideMotor;
    public Servo dumperServo;
    public Outtake(final HardwareMap hMap) {
        slideMotor = hMap.get(DcMotor.class, "slideMotor");
        dumperServo = hMap.get(Servo.class, "dumperServo");
    }

    public void lowerSlides() {
        slideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slideMotor.setTargetPosition(100);
        slideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slideMotor.setPower(0.3);
    }

    public void raiseSlides() {
        slideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slideMotor.setTargetPosition(-100);
        slideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slideMotor.setPower(0.3);
    }

    public void returnBox() {
        dumperServo.setPosition(0.7);
    }

    public void emptyBox() {
        dumperServo.setPosition(0.4);
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
        if (gamepad2.right_bumper) extendOuttake();
        if (gamepad2.left_bumper) retractOuttake();
        if (gamepad2.y) emptyBox();
        if (gamepad2.a) returnBox();
        // hang
        if (gamepad2.right_trigger > 0.1) slideMotor.setPower(-0.5);
        else if (gamepad2.left_trigger > 0.1) slideMotor.setPower(0.5);
        else slideMotor.setPower(0);
    }
}