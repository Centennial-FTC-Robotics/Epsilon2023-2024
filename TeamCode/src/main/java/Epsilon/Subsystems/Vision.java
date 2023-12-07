// ignore this its broken

package Epsilon.Subsystems;

import static android.os.SystemClock.sleep;

import com.arcrobotics.ftclib.command.SubsystemBase;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.ArrayList;
import Epsilon.Subsystem;
import com.qualcomm.robotcore.hardware.Gamepad;


public class Vision implements Subsystem {
    OpenCvCamera camera;
    AprilTagDetectionPipeline aprilTagDetectionPipeline;
    BlueCubeDetectionPipeline blueCubeDetectionPipeline;

    static final double FEET_PER_METER = 3.28084;

    // Lens intrinsics
    // UNITS ARE PIXELS
    // NOTE: this calibration is for the C920 webcam at 800x448.
    // You will need to do your own calibration for other configurations!
    double fx = 578.272;
    double fy = 578.272;
    double cx = 402.145;
    double cy = 221.506;

    // UNITS ARE METERS
    double tagsize = 0.166;

    // Tag ID 1,2,3 from the 36h11 family
    int LEFT = 1;
    int MIDDLE = 2;
    int RIGHT = 3;

    AprilTagDetection tagOfInterest = null;

    public Vision(final HardwareMap hMap) {
        int cameraMonitorViewId = hMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createWebcam(hMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        //aprilTagDetectionPipeline = new AprilTagDetectionPipeline(tagsize, fx, fy, cx, cy);
        blueCubeDetectionPipeline = new BlueCubeDetectionPipeline(3.5);

        camera.setPipeline(blueCubeDetectionPipeline);
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                camera.startStreaming(800,448, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode) {

            }
        });
    }

    public void detectTag(LinearOpMode opMode) {
        opMode.telemetry.setMsTransmissionInterval(50);

        ArrayList<AprilTagDetection> currentDetections = aprilTagDetectionPipeline.getLatestDetections();

        if(currentDetections.size() != 0)
        {
            boolean tagFound = false;

            for(AprilTagDetection tag : currentDetections)
            {
                if(tag.id == LEFT || tag.id == MIDDLE || tag.id == RIGHT)
                {
                    tagOfInterest = tag;
                    tagFound = true;
                    break;
                }
            }

            if(tagFound)
            {
                opMode.telemetry.addLine("Tag of interest is in sight!\n\nLocation data:");
                tagToTelemetry(tagOfInterest, opMode);
            }
            else
            {
                opMode.telemetry.addLine("Don't see tag of interest :(");

                if(tagOfInterest == null)
                {
                    opMode.telemetry.addLine("(The tag has never been seen)");
                }
                else
                {
                    opMode.telemetry.addLine("\nBut we HAVE seen the tag before; last seen at:");
                    tagToTelemetry(tagOfInterest, opMode);
                }
            }

        }
        else
        {
            opMode.telemetry.addLine("Don't see tag of interest :(");

            if(tagOfInterest == null)
            {
                opMode.telemetry.addLine("(The tag has never been seen)");
            }
            else
            {
                opMode.telemetry.addLine("\nBut we HAVE seen the tag before; last seen at:");
                tagToTelemetry(tagOfInterest, opMode);
            }

        }

        opMode.telemetry.update();
        sleep(20);
    }

    public void detectCube(LinearOpMode opMode) {
        opMode.telemetry.setMsTransmissionInterval(50);

        if(blueCubeDetectionPipeline.isCubeDetected())
        {
            opMode.telemetry.addLine("Cube is in sight!\n\nLocation data:");
            opMode.telemetry.addLine(Double.toString(blueCubeDetectionPipeline.getCubeCenterX()));
            opMode.telemetry.addLine(Double.toString(blueCubeDetectionPipeline.getCubeCenterY()));
            opMode.telemetry.update();
        }
        else
        {
            opMode.telemetry.addLine("Don't see cube :(");
            opMode.telemetry.update();
        }
    }

    void tagToTelemetry(AprilTagDetection detection, LinearOpMode opMode)
    {
        opMode.telemetry.addLine(String.format("\nDetected tag ID=%d", detection.id));
        opMode.telemetry.addLine(String.format("Translation X: %.2f feet", detection.pose.x*FEET_PER_METER));
        opMode.telemetry.addLine(String.format("Translation Y: %.2f feet", detection.pose.y*FEET_PER_METER));
        opMode.telemetry.addLine(String.format("Translation Z: %.2f feet", detection.pose.z*FEET_PER_METER));
        /*opMode.telemetry.addLine(String.format("Rotation Yaw: %.2f degrees", Math.toDegrees(detection.pose.yaw)));
        opMode.telemetry.addLine(String.format("Rotation Pitch: %.2f degrees", Math.toDegrees(detection.pose.pitch)));
        opMode.telemetry.addLine(String.format("Rotation Roll: %.2f degrees", Math.toDegrees(detection.pose.roll)));*/
    }

    public void teleOpUpdate(Gamepad gamepad1, Gamepad gamepad2) {}
}