package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import Epsilon.ElementProcessor;
import Epsilon.OurRobot;
import Epsilon.Subsystems.Vision;

@Autonomous
public class FrontBlueVisionAuto extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        OurRobot robot = new OurRobot(this);
        robot.vision = new Vision(hardwareMap, true);

        waitForStart();

        ElementProcessor.PropPositions cubeLocation = robot.vision.detectElement();

        robot.drivetrain.move(28, "drive");
        if (cubeLocation == ElementProcessor.PropPositions.RIGHT) {
            robot.drivetrain.move(23, "rotate");
            Thread.sleep(1000);
            robot.drivetrain.move(-23, "rotate");
        } else if (cubeLocation == ElementProcessor.PropPositions.LEFT) {
            robot.drivetrain.move(-23, "rotate");
            Thread.sleep(1000);
            robot.drivetrain.move(23, "rotate");
        } else if (cubeLocation == ElementProcessor.PropPositions.MIDDLE) {
            Thread.sleep(1000);
        } else {
            telemetry.addLine("cube not found");
            telemetry.update();
        }
        robot.drivetrain.move(26, "drive");
        robot.drivetrain.move(-21, "rotate");
        robot.drivetrain.move(72, "drive");
        robot.drivetrain.move(-21, "rotate");
        robot.drivetrain.move(52, "drive");
        robot.drivetrain.move(26, "strafe");
    }
}
