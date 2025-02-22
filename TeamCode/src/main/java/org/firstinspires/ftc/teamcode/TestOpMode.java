package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.RevIMU;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import Epsilon.ElementProcessor;
import Epsilon.OurRobot;
import Epsilon.Subsystems.Vision;

@TeleOp
public class TestOpMode extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        OurRobot robot = new OurRobot(this);
        waitForStart();
        robot.intake.spinWheel(-0.5);
        Thread.sleep(2000);
        robot.intake.spinWheel(0);
    }
}
