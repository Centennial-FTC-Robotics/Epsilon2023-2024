package Epsilon.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import Epsilon.Subsystem;
import com.qualcomm.robotcore.hardware.Gamepad;

public class Drivetrain implements Subsystem {
    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor backLeft;
    public DcMotor backRight;
    public IMU imu_class;
    public Drivetrain(final HardwareMap hMap) {
        frontLeft = hMap.get(DcMotor.class, "frontLeft");
        frontRight = hMap.get(DcMotor.class, "frontRight");
        backLeft = hMap.get(DcMotor.class, "backLeft");
        backRight = hMap.get(DcMotor.class, "backRight");
        imu_class = new IMU(hMap);
    }

    public void teleOpUpdate(Gamepad gamepad1) {
        double drive = -gamepad1.left_stick_y;
        double strafe = gamepad1.left_stick_x;
        double turn = gamepad1.right_stick_x;

        double angle = imu_class.imu.getAngularOrientation().firstAngle;
        double rot_x = strafe * Math.cos(Math.toRadians(angle)) - drive * Math.sin(Math.toRadians(angle));
        double rot_y = strafe * Math.sin(Math.toRadians(angle)) + drive * Math.cos(Math.toRadians(angle));

        double frontLeftPower = rot_y+turn+rot_x;
        double frontRightPower = rot_y-turn-rot_x;
        double backLeftPower = rot_y+turn-rot_x;
        double backRightPower = rot_y-turn+rot_x;

        frontLeft.setPower(frontLeftPower);
        frontRight.setPower(frontRightPower);
        backLeft.setPower(backLeftPower);
        backRight.setPower(backRightPower);
    }
}
