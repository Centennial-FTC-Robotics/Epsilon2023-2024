package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import Epsilon.OurRobot;

@Autonomous
public class FrontRedParkAuto extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        OurRobot robot = new OurRobot(this);

        waitForStart();

        Thread.sleep(10000);
        robot.drivetrain.move(54, "drive");
        robot.drivetrain.move(20.5, "rotate");
        robot.drivetrain.move(72, "drive");
        robot.drivetrain.move(20.5, "rotate");
        robot.drivetrain.move(26, "drive");
        robot.drivetrain.move(-20.5, "rotate");
        robot.drivetrain.move(10, "drive");
        robot.drivetrain.move(26, "strafe");
        robot.drivetrain.move(14, "drive");
    }
}
