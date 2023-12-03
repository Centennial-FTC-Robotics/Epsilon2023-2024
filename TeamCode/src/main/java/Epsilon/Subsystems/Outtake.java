package Epsilon.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import Epsilon.Subsystem;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

public class Outtake implements Subsystem {
    public DcMotor leftSlide;
    public DcMotor rightSlide;
    public Servo boxServo1;
    public Servo boxServo2;
    public Outtake(final HardwareMap hMap) {
        leftSlide = hMap.get(DcMotor.class, "leftSlide");
        rightSlide = hMap.get(DcMotor.class, "rightSlide");
        boxServo1 = hMap.get(Servo.class, "boxServo1");
        boxServo2 = hMap.get(Servo.class, "boxServo2");
    }

    public void raiseSlides() {
        leftSlide.setPower(0.5);
        rightSlide.setPower(0.5);
    }

    public void lowerSlides() {
        leftSlide.setPower(-0.5);
        rightSlide.setPower(-0.5);
    }

    public void stopSlides() {
        leftSlide.setPower(0);
        rightSlide.setPower(0);
    }

    public void emptyBox() {
        boxServo1.setPosition(0.5);
        boxServo2.setPosition(0.5);
    }

    public void returnBox() {
        boxServo1.setPosition(0);
        boxServo2.setPosition(0);
    }

    public void teleOpUpdate(Gamepad gamepad1) {
        if (gamepad1.left_bumper) lowerSlides();
        else if (gamepad1.right_bumper) raiseSlides();
        else stopSlides();

        if (gamepad1.x) emptyBox();
        else returnBox();
    }
}
