package Epsilon.Subsystems;

import org.opencv.core.MatOfPoint;
import org.openftc.easyopencv.OpenCvPipeline;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.ArrayList;

public class BlueCubeDetectionPipeline extends OpenCvPipeline {
    private Mat hsvMat = new Mat();
    private Mat maskMat = new Mat();
    private ArrayList<MatOfPoint> contourMat = new ArrayList<>();

    private double cubeCenterX = -1;
    private double cubeCenterY = -1;
    private boolean cubeDetected = false;

    private MatOfPoint largestContour = null;
    private Rect largestRect = null;

    Telemetry telemetry;

    public static Scalar lowerBlue = new Scalar(100, 75, 0);
    public static Scalar upperBlue = new Scalar(150, 150, 250);

    public BlueCubeDetectionPipeline(Telemetry telemetry) {
        this.telemetry = telemetry;
    }

    @Override
    public Mat processFrame(Mat input) {
        Imgproc.cvtColor(input, hsvMat, Imgproc.COLOR_RGB2HSV);
        Core.inRange(hsvMat, lowerBlue, upperBlue, maskMat);
        Imgproc.findContours(maskMat, contourMat, new Mat(), Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
        cubeCenterX = -1;
        cubeCenterY = -1;
        cubeDetected = false;

        int MIN_CONTOUR_AREA_THRESHOLD = 0;

        telemetry.addLine(Integer.toString(contourMat.size()));
        largestContour = null;
        largestRect = null;

        for (int i = 0; i < contourMat.size(); i++) {
            Rect rect = Imgproc.boundingRect(contourMat.get(i));

            double area = Imgproc.contourArea(contourMat.get(i));
            if (area > MIN_CONTOUR_AREA_THRESHOLD) {
                if (largestContour == null || area > Imgproc.contourArea(largestContour)) {
                    largestContour = contourMat.get(i);
                    largestRect = rect;
                }
            }
        }

        if (largestContour != null) {
            Imgproc.rectangle(input, largestRect.tl(), largestRect.br(), new Scalar(0, 255, 0), 2);

            cubeCenterX = largestRect.x + largestRect.width / 2.0;
            cubeCenterY = largestRect.y + largestRect.height / 2.0;

            telemetry.addLine("Cube Detected");
            telemetry.addData("Center X", cubeCenterX);
            telemetry.addData("Center Y", cubeCenterY);
            telemetry.update();

            cubeDetected = true;
        }

        return input;
    }

    public double getCubeCenterX() {
        return cubeCenterX;
    }

    public double getCubeCenterY() {
        return cubeCenterY;
    }

    public boolean isCubeDetected() {
        return cubeDetected;
    }
}
