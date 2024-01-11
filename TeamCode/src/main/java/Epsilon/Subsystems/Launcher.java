package Epsilon.Subsystems;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import Epsilon.Subsystem;

public class Launcher implements Subsystem {
    public Servo launcher;
    public Launcher(final HardwareMap hMap) {
        launcher = hMap.get(Servo.class, "launcherServo");
    }

    public void launch() {
        launcher.setPosition(0.5);
    }

    public void teleOpUpdate(Gamepad gamepad1, Gamepad gamepad2) {
        if (gamepad2.dpad_left) {
            launch();
        }
    }
}
