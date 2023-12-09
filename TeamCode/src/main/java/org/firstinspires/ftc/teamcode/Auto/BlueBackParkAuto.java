package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import Epsilon.OurRobot;

@Autonomous
public class BlueBackParkAuto extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        OurRobot robot = new OurRobot(this);

        waitForStart();

        robot.drivetrain.move(24, "drive");
        robot.drivetrain.move(-23,"rotate");
        robot.drivetrain.move(76, "drive");
        robot.drivetrain.move(-24, "strafe");
        robot.drivetrain.move(24, "drive");
    }
}
