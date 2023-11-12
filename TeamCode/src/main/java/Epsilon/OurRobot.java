package Epsilon;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import Epsilon.Subsystems.Drivetrain;
import Epsilon.Subsystems.IMU;
import Epsilon.Subsystems.Intake;
import Epsilon.Subsystems.Outtake;
import Epsilon.Subsystems.Vision;

public class OurRobot {
    public Drivetrain drivetrain;
    public IMU imu;
    public Intake intake;
    public Outtake outtake;

    public Vision vision;


    public OurRobot(LinearOpMode opMode) {
        drivetrain = new Drivetrain(opMode.hardwareMap, "drivetrain");
        imu = new IMU(opMode.hardwareMap, "imu");
        intake = new Intake(opMode.hardwareMap, "intake");
        outtake = new Outtake(opMode.hardwareMap, "outtake");
        vision = new Vision(opMode.hardwareMap, "vision");
    }
}
