package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import Epsilon.OurRobot;

@Autonomous
public class BlueBackVisionAuto extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        OurRobot robot = new OurRobot(this);

        waitForStart();

        if (robot.vision.detectCube() >= 120) {
            robot.drivetrain.move(46, "rotate");
            robot.drivetrain.move(24, "drive");
            robot.drivetrain.move(23, "rotate");
            robot.intake.spinWheel(0.5);
            robot.drivetrain.move(46, "rotate");
        } else if (robot.vision.detectCube() <= 60) {
            robot.drivetrain.move(46, "rotate");
            robot.drivetrain.move(24, "drive");
            robot.drivetrain.move(-23, "rotate");
            robot.intake.spinWheel(0.5);
        } else {
            robot.drivetrain.move(46, "rotate");
            robot.drivetrain.move(24, "drive");
            robot.intake.spinWheel(0.5);
            robot.drivetrain.move(-23, "rotate");
        }
        robot.drivetrain.move(76, "drive");
        robot.drivetrain.move(-24, "strafe");
        robot.drivetrain.move(24, "drive");
    }
}
