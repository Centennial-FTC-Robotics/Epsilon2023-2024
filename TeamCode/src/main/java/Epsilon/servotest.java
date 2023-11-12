package Epsilon;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;

@TeleOp
public class servotest extends LinearOpMode {
    public Servo intakeServo;
    @Override
    public void runOpMode() throws InterruptedException {
        intakeServo = hardwareMap.get(Servo.class, "intakeServo");
        waitForStart();
        while (opModeIsActive()) {
            intakeServo.setPosition(gamepad1.left_stick_y);
        }
    }
}