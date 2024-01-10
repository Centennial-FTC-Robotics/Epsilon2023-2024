package Epsilon;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import Epsilon.Subsystems.Drivetrain;
import Epsilon.Subsystems.IMU;
import Epsilon.Subsystems.Intake;
import Epsilon.Subsystems.Outtake;
import Epsilon.Subsystems.Vision;
import Epsilon.Subsystems.Launcher;
import com.qualcomm.robotcore.hardware.Gamepad;

public class OurRobot {
    public Drivetrain drivetrain;
    public IMU imu;
    public Intake intake;
    public Outtake outtake;
    public Vision vision;
    public Launcher launcher;

    public OurRobot(LinearOpMode opMode) {
        drivetrain = new Drivetrain(opMode.hardwareMap);
        imu = new IMU(opMode.hardwareMap);
        intake = new Intake(opMode.hardwareMap);
        launcher = new Launcher(opMode.hardwareMap);
        //outtake = new Outtake(opMode.hardwareMap);
    }

    public void teleOpUpdate(Gamepad gamepad1, Gamepad gamepad2) throws InterruptedException {
        drivetrain.teleOpUpdate(gamepad1, gamepad2);
        intake.teleOpUpdate(gamepad1, gamepad2);
        launcher.teleOpUpdate(gamepad1, gamepad2);
        //outtake.teleOpUpdate(gamepad1, gamepad2);
    }

}
