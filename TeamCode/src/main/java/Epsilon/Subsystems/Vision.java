// ignore this its broken

package Epsilon.Subsystems;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.opencv.core.Scalar;
import org.firstinspires.ftc.vision.VisionPortal;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;

import Epsilon.Subsystem;
import Epsilon.ElementProcessor;

// thanks joel!
public class Vision implements Subsystem {

    OpenCvCamera camera;
    double minArea = 100;

    Scalar blueL = new Scalar(75, 50, 0);
    Scalar blueH = new Scalar(120, 255, 255);


    Scalar redL = new Scalar(0, 155, 0);
    Scalar redH = new Scalar(35, 255, 255);

    Scalar low;
    Scalar high;

    VisionPortal portal;
    ElementProcessor processor;

    public void teleOpUpdate(Gamepad gamepad1, Gamepad gamepad2) {
    }

    public Vision(final HardwareMap hMap, boolean weAreBlue, LinearOpMode opmode) {
        int cameraMonitorViewId = hMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createWebcam(hMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);

        if(weAreBlue) {
            low = blueL;
            high = blueH;
        } else {
            low = redL;
            high = redH;
        }

        processor = new ElementProcessor(low, high, () -> minArea, () -> 213, () -> 426);
        portal = new VisionPortal.Builder().setCamera(hMap.get(WebcamName.class, "Webcam 1")).addProcessor(processor).build();
    }

    public ElementProcessor.PropPositions detectElement() {
        long start = System.currentTimeMillis();
        ElementProcessor.PropPositions pos = processor.getRecordedPropPosition();
        while(System.currentTimeMillis()-start < 5000 && pos == ElementProcessor.PropPositions.UNFOUND) {
            pos = processor.getRecordedPropPosition();
        }

        return processor.getRecordedPropPosition();
    }
}