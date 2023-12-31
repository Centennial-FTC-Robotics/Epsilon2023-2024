package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.RevIMU;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import Epsilon.ElementProcessor;
import Epsilon.OurRobot;
import Epsilon.Subsystems.Vision;

@TeleOp
public class TestOpMode extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        OurRobot robot = new OurRobot(this);
        robot.vision = new Vision(hardwareMap, true);

        waitForStart();

        if (robot.vision.detectElement() == ElementProcessor.PropPositions.LEFT) {
            telemetry.addLine("left");
        } else if (robot.vision.detectElement() == ElementProcessor.PropPositions.MIDDLE) {
            telemetry.addLine("middle");
        } else if (robot.vision.detectElement() == ElementProcessor.PropPositions.RIGHT) {
            telemetry.addLine("right");
        } else {
            telemetry.addLine("not found");
        }
        telemetry.update();

        while (!isStopRequested()) {
            robot.teleOpUpdate(gamepad1, gamepad2);
        }
    }
}
