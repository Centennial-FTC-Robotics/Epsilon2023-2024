package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import Epsilon.OurRobot;

@Autonomous
public class RedFrontParkAuto extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        OurRobot robot = new OurRobot(this);

        waitForStart();

        robot.drivetrain.move(24, false);
        robot.drivetrain.move(24, true);
        robot.drivetrain.move(-24, false);
        robot.drivetrain.move(24, true);
    }
}
