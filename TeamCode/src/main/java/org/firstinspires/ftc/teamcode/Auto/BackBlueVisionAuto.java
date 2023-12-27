package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import Epsilon.ElementProcessor;
import Epsilon.OurRobot;
import Epsilon.Subsystems.Vision;

@Autonomous
public class BackBlueVisionAuto extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        OurRobot robot = new OurRobot(this);
        robot.vision = new Vision(hardwareMap, true);

        waitForStart();

        ElementProcessor.PropPositions cubeLocation = robot.vision.detectElement();

        if (cubeLocation == ElementProcessor.PropPositions.RIGHT) {
            robot.drivetrain.move(24, "drive");
            robot.drivetrain.move(23, "rotate");
            robot.intake.spinWheel(0.5);
            robot.drivetrain.move(46, "rotate");
        } else if (cubeLocation == ElementProcessor.PropPositions.LEFT) {
            robot.drivetrain.move(24, "drive");
            robot.drivetrain.move(-23, "rotate");
            robot.intake.spinWheel(0.5);
        } else if (cubeLocation == ElementProcessor.PropPositions.MIDDLE) {
            robot.drivetrain.move(24, "drive");
            robot.intake.spinWheel(0.5);
            robot.drivetrain.move(-23, "rotate");
        } else {
            telemetry.addLine("cube not found");
            telemetry.update();
        }
        robot.drivetrain.move(24, "drive");
        robot.drivetrain.move(-24, "strafe");
        robot.drivetrain.move(24, "drive");
    }
}
