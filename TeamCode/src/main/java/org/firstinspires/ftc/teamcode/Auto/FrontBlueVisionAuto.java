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
            robot.drivetrain.move(20.5, "rotate");
            robot.intake.lowerIntake();
            robot.intake.spinWheel(0.5);
            Thread.sleep(1000);
            robot.intake.spinWheel(0);
            robot.drivetrain.move(-20.5, "rotate");
        } else if (cubeLocation == ElementProcessor.PropPositions.LEFT) {
            robot.drivetrain.move(-20.5, "rotate");
            robot.intake.lowerIntake();
            robot.intake.spinWheel(0.5);
            Thread.sleep(1000);
            robot.intake.spinWheel(0);
            robot.drivetrain.move(20.5, "rotate");
        } else if (cubeLocation == ElementProcessor.PropPositions.MIDDLE) {
            robot.intake.lowerIntake();
            robot.intake.spinWheel(0.5);
            Thread.sleep(1000);
            robot.intake.spinWheel(0);
        } else {
            telemetry.addLine("cube not found");
            telemetry.update();
        }
        robot.drivetrain.move(26, "drive");
        robot.drivetrain.move(-20.5, "rotate");
        robot.drivetrain.move(72, "drive");
        robot.drivetrain.move(-20.5, "rotate");
        robot.drivetrain.move(50, "drive");
        robot.drivetrain.move(26, "strafe");
    }
}
