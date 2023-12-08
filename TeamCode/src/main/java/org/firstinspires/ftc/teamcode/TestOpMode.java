package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.RevIMU;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import Epsilon.OurRobot;

@TeleOp
public class TestOpMode extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        OurRobot robot = new OurRobot(this);

        waitForStart();

        robot.drivetrain.move(24, false);
        robot.drivetrain.move(72, true);
        robot.drivetrain.move(-24, false);
        robot.drivetrain.move(24, true);

        while (!isStopRequested()) {
            //robot.teleOpUpdate(gamepad1, gamepad2);
            //robot.vision.detectCube(this);

            //robot.vision.detectTag(this);
        }
    }
}
