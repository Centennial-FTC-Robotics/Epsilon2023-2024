package Epsilon.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import Epsilon.Subsystem;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

public class Drivetrain implements Subsystem {
    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor backLeft;
    public DcMotor backRight;
    private boolean slowMode = false;

    public Drivetrain(final HardwareMap hMap) {
        frontLeft = hMap.get(DcMotor.class, "frontLeft");
        frontRight = hMap.get(DcMotor.class, "frontRight");
        backLeft = hMap.get(DcMotor.class, "backLeft");
        backRight = hMap.get(DcMotor.class, "backRight");

        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);

        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    private double inches_to_ticks(double inches) {
        double wheel_diameter = 95 / 25.4;
        double circumference = wheel_diameter * Math.PI;
        double revs = inches / circumference;
        return revs * 537.6;
    }

    public void move(double inches, String mode) {
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        int position = (int) inches_to_ticks(inches);
        if (mode == "drive") {
            frontLeft.setTargetPosition(-position);
            frontRight.setTargetPosition(-position);
            backLeft.setTargetPosition(-position);
            backRight.setTargetPosition(-position);
        } else if (mode == "strafe") {
            frontLeft.setTargetPosition(-position);
            frontRight.setTargetPosition(position);
            backLeft.setTargetPosition(position);
            backRight.setTargetPosition(-position);
        } else if (mode == "rotate") {
            frontLeft.setTargetPosition(-position);
            frontRight.setTargetPosition(position);
            backLeft.setTargetPosition(-position);
            backRight.setTargetPosition(position);
        }

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeft.setPower(0.3);
        frontRight.setPower(0.3);
        backLeft.setPower(0.3);
        backRight.setPower(0.3);

        while (frontLeft.isBusy() || frontRight.isBusy() || backLeft.isBusy() || backRight.isBusy()) {}

    }

    public void teleOpUpdate(Gamepad gamepad1, Gamepad gamepad2) {
        if (gamepad1.right_bumper) slowMode = !slowMode;

        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        double drive = gamepad1.left_stick_y;
        double strafe = -1.65*gamepad1.left_stick_x;
        double turn = -gamepad1.right_stick_x;
        double frontLeftPower, frontRightPower, backLeftPower, backRightPower;
        if (slowMode) {
            frontLeftPower = Range.clip(drive + strafe + turn, -0.3, 0.3);
            frontRightPower = Range.clip(drive - strafe - turn, -0.3, 0.3);
            backLeftPower = Range.clip(drive - strafe + turn, -0.3, 0.3);
            backRightPower = Range.clip(drive + strafe - turn, -0.3, 0.3);
        } else {
            frontLeftPower = Range.clip(drive + strafe + turn, -0.8, 0.8);
            frontRightPower = Range.clip(drive - strafe - turn, -0.8, 0.8);
            backLeftPower = Range.clip(drive - strafe + turn, -0.8, 0.8);
            backRightPower = Range.clip(drive + strafe - turn, -0.8, 0.8);
        }
        frontLeft.setPower(frontLeftPower);
        frontRight.setPower(frontRightPower);
        backLeft.setPower(backLeftPower);
        backRight.setPower(backRightPower);
    }
}
