//this will work maybe!!
package Epsilon.Subsystems;

import org.opencv.core.MatOfPoint;
import org.openftc.easyopencv.OpenCvPipeline;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;

public class BlueCubeDetectionPipeline extends OpenCvPipeline {
    private Mat hsvMat = new Mat();
    private Mat maskMat = new Mat();
    private ArrayList<MatOfPoint> contourMat = new ArrayList<>();

    private double cubeCenterX = -1;
    private double cubeCenterY = -1;
    private boolean cubeDetected = false;

    private final double cubeSize;

    public BlueCubeDetectionPipeline(double cubeSize) {
        this.cubeSize = cubeSize;
    }

    @Override
    public Mat processFrame(Mat input) {
        Imgproc.cvtColor(input, hsvMat, Imgproc.COLOR_RGB2HSV);
        Scalar lowerBlue = new Scalar(120, 100, 255); //can change later if needed ig
        Scalar upperBlue = new Scalar(160, 185, 255); //can change later if needed ig
        Core.inRange(hsvMat, lowerBlue, upperBlue, maskMat);
        Imgproc.findContours(maskMat, contourMat, new Mat(), Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
        cubeCenterX = -1;
        cubeCenterY = -1;
        cubeDetected = false;

        if (!contourMat.isEmpty()) {
            for (int i = 0; i < contourMat.size(); i++) {
                Rect rect = Imgproc.boundingRect(contourMat.get(i));
                //double contourArea = Imgproc.contourArea(contourMat.get(i));
                cubeCenterX = rect.x + rect.width / 2.0;
                cubeCenterY = rect.y + rect.height / 2.0;
                cubeDetected = true;
                Imgproc.rectangle(input, rect.tl(), rect.br(), new Scalar(0, 255, 0), 2);
                break;
            }
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