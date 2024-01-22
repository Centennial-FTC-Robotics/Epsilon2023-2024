package Epsilon.Subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import Epsilon.Subsystem;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@Config
public class Outtake implements Subsystem {

    public static double feedforward = 0;

    public static double dumperIn = 0.5;
    public static double dumperOut = 0.23;

    public static double wheelOutMult = 1;

    public DcMotor slideMotor;
    //public DcMotor slideMotor2;
    public Servo dumperServo;
    public CRServo wheel;

    public Outtake(final HardwareMap hMap) {

        slideMotor = hMap.get(DcMotor.class, "slideMotor");
        //slideMotor2 = hMap.get(DcMotor.class, "slideMotor2");

        dumperServo = hMap.get(Servo.class, "dumperServo");
        wheel = hMap.get(CRServo.class, "boxWheel");

        slideMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }

    public void teleOpUpdate(Gamepad gamepad1, Gamepad gamepad2) {

        slideMotor.setPower(Range.clip(-gamepad2.left_stick_y+feedforward, -1, 1));
        //slideMotor2.setPower(Range.clip(gamepad2.left_stick_y+feedforward, -1, 1));

        if(gamepad2.right_bumper)
            dumperServo.setPosition(dumperOut);
        if(gamepad2.left_bumper)
            dumperServo.setPosition(dumperIn);

        if(gamepad2.b) {
            wheel.setPower(wheelOutMult);
        } else if(gamepad2.right_trigger > 0) {
            wheel.setPower(-wheelOutMult);
        } else {
            wheel.setPower(0);
        }


    }
}