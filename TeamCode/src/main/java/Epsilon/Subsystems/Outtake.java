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
    public Servo doorServo;
    public Outtake(final HardwareMap hMap) {
        slideServo1 = hMap.get(Servo.class, "slideServo1");
        dumperServo = hMap.get(Servo.class, "dumperServo");
        doorServo = hMap.get(Servo.class, "doorServo");
    }

    public void raiseSlides() {
        slideServo1.setPosition(0.5);
        System.out.println("hereRaise");
    }

    public void lowerSlides() {
        slideServo1.setPosition(0);
        System.out.println("hereLower");
    }

    public void openDoor() {
        doorServo.setPosition(0);
    }

    public void closeDoor() {
        doorServo.setPosition(0.5);
    }

    public void stopSlides() {
        //leftSlide.setPower(0);
        //rightSlide.setPower(0);
    }

    public void emptyBox() {
        dumperServo.setPosition(0.2);
        //doorServo.setPosition(1);
        System.out.println("hereEmpty");
    }

    public void returnBox() {
        dumperServo.setPosition(0.9);
        //doorServo.setPosition(0.5);
    }

    public void teleOpUpdate(Gamepad gamepad1, Gamepad gamepad2) {
        if (gamepad2.left_bumper) lowerSlides();
        else if (gamepad2.right_bumper) raiseSlides();
        else stopSlides();

        if (gamepad2.right_trigger > 0.1) openDoor();
        else closeDoor();

        if (gamepad2.y) emptyBox();
        else if(gamepad2.a) returnBox();
    }
}