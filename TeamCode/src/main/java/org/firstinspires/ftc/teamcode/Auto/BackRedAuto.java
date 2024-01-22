package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import Epsilon.ElementProcessor;
import Epsilon.OurRobot;
import Epsilon.Subsystems.Vision;

@Autonomous
public class BackRedAuto extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        OurRobot robot = new OurRobot(this);
        robot.vision = new Vision(hardwareMap, false);

        waitForStart();

        ElementProcessor.PropPositions cubeLocation = robot.vision.detectElement();

        robot.drivetrain.move(28, "drive");
        if (cubeLocation == ElementProcessor.PropPositions.RIGHT) {
            robot.drivetrain.move(-20.5, "rotate");
            robot.drivetrain.move(-24, "drive");
            //robot.intake.lowerIntake();
            robot.intake.spinWheel(-0.5);
            Thread.sleep(2000);
            robot.intake.spinWheel(0);
        } else if (cubeLocation == ElementProcessor.PropPositions.LEFT) {
            robot.drivetrain.move(-20.5, "rotate");
            //robot.intake.lowerIntake();
            robot.intake.spinWheel(-0.5);
            Thread.sleep(2000);
            robot.intake.spinWheel(0);
            robot.drivetrain.move(-24, "drive");
        } else {
            //robot.intake.lowerIntake();
            robot.intake.spinWheel(-0.5);
            Thread.sleep(2000);
            robot.intake.spinWheel(0);
            robot.drivetrain.move(-20.5, "rotate");
            robot.drivetrain.move(-24, "drive");
        }
//        robot.outtake.extendOuttake();
//        robot.outtake.retractOuttake();
        robot.drivetrain.move(-30, "strafe");
        robot.drivetrain.move(-24, "drive");
    }
}
