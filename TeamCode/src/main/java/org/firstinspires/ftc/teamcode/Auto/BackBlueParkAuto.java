package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import Epsilon.OurRobot;

@Autonomous
public class BackBlueParkAuto extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        OurRobot robot = new OurRobot(this);

        waitForStart();

        robot.drivetrain.move(28, "drive");
        robot.drivetrain.move(-20.5, "rotate");
        robot.drivetrain.move(34, "drive");
        robot.drivetrain.move(-30, "strafe");
        robot.drivetrain.move(14, "drive");
    }
}
