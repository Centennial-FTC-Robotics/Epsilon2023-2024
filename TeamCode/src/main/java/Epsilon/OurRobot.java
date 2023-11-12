package Epsilon;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import Epsilon.Subsystems.Drivetrain;
import Epsilon.Subsystems.IMU;
import Epsilon.Subsystems.Intake;
import Epsilon.Subsystems.Outtake;

public class OurRobot {
    public Drivetrain drivetrain;
    public IMU imu;
    public Intake intake;
    public Outtake outtake;

    public OurRobot(LinearOpMode opMode) {
        //drivetrain = new Drivetrain(opMode.hardwareMap);
        //imu = new IMU(opMode.hardwareMap);
        intake = new Intake(opMode.hardwareMap);
        //outtake = new Outtake(opMode.hardwareMap);
    }
}
