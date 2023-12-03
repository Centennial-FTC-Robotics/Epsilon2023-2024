package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.RevIMU;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import Epsilon.OurRobot;

@TeleOp
public class TestOpMode extends LinearOpMode {
    static final boolean FIELD_CENTRIC = true;

    @Override
    public void runOpMode() throws InterruptedException {
        OurRobot robot = new OurRobot(this);

        /*MecanumDrive drive = new MecanumDrive(
                new Motor(hardwareMap, "frontLeft", Motor.GoBILDA.RPM_435),
                new Motor(hardwareMap, "frontRight", Motor.GoBILDA.RPM_435),
                new Motor(hardwareMap, "backLeft", Motor.GoBILDA.RPM_435),
                new Motor(hardwareMap, "backRight", Motor.GoBILDA.RPM_435)
        );*/

        RevIMU imu = new RevIMU(hardwareMap);
        imu.init();

        GamepadEx driverOp = new GamepadEx(gamepad1);

        waitForStart();

        while (!isStopRequested()) {
            /*if (!FIELD_CENTRIC) {
                drive.driveRobotCentric(
                        driverOp.getLeftX(),
                        driverOp.getLeftY(),
                        driverOp.getRightX(),
                        false
                );*/
            //} else {
                robot.intake.teleOpUpdate(gamepad1);

                //robot.vision.detectTag(this);
                /*drive.driveFieldCentric(
                        driverOp.getLeftX(),
                        driverOp.getLeftY(),
                        driverOp.getRightX(),
                        imu.getRotation2d().getDegrees(),   // gyro value passed in here must be in degrees
                        false
                );*/

            //}
        }
    }
}
