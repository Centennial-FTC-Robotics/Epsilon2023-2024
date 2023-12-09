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

        //robot.drivetrain.move(21, "rotate");
        if (robot.vision.detectCube() >= 120) {
            robot.drivetrain.move(42,"rotate");
            robot.drivetrain.move(24, "drive");
            robot.drivetrain.move(21,"rotate");
            robot.intake.spinWheel(0.5);
        } else if (robot.vision.detectCube() <= 60) {
            robot.drivetrain.move(42,"rotate");
            robot.drivetrain.move(24, "drive");
            robot.drivetrain.move(-21,"rotate");
            robot.intake.spinWheel(0.5);
            robot.drivetrain.move(42,"rotate");
        } else {
            robot.drivetrain.move(42,"rotate");
            robot.drivetrain.move(24, "drive");
            robot.intake.spinWheel(0.5);
            robot.drivetrain.move(21,"rotate");
        }
        robot.drivetrain.move(76, "drive");
        robot.drivetrain.move(26, "strafe");
        robot.drivetrain.move(26, "drive");

        while (!isStopRequested()) {
            robot.teleOpUpdate(gamepad1, gamepad2);
            //robot.vision.detectTag(this);
        }
    }
}
