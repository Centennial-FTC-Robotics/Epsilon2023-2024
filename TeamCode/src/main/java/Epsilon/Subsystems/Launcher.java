package Epsilon.Subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import Epsilon.Subsystem;

@Config
public class Launcher implements Subsystem {

    public static double springPower = 0;


    public Servo launcher;
//    public DcMotor winchMotor;


    public Launcher(final HardwareMap hMap) {

//        winchMotor = hMap.get(DcMotor.class, "winch");


        launcher = hMap.get(Servo.class, "launcherServo");
        launcher.setPosition(0.8);
    }

    public void launch() {
        launcher.setPosition(0.5);
    }

    public void teleOpUpdate(Gamepad gamepad1, Gamepad gamepad2) {
        if (gamepad2.left_stick_button && gamepad2.right_stick_button) {
            launch();
        }
//        if(Math.abs(gamepad2.right_stick_y) > 0) {
//            winchMotor.setPower(gamepad2.right_stick_y);
//        } else {
//            winchMotor.setPower(springPower);
//        }
    }
}
