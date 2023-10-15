package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MeepMeepTesting {

    public static RoadRunnerBotEntity bottomRed(MeepMeep meepMeep) {
        return new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(62, -34, Math.toRadians(180)))
                                .forward(24)
                                .turn(Math.toRadians(-90))
                                .forward(78)
                                .turn(Math.toRadians(180))
                                .forward(98)
                                .turn(Math.toRadians(180))
                                .forward(98)
                                .turn(Math.toRadians(180))
                                .forward(98)
                                .turn(Math.toRadians(180))
                                .forward(98)
                                .strafeTo(new Vector2d(60,50))
                                .forward(10)
                                .build()
                );
    }
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = bottomRed(meepMeep);

        Image img = null;
        try { img = ImageIO.read(new File("MeepMeepTesting\\src\\main\\java\\com\\example\\meepmeeptesting\\background.png")); }
        catch (IOException e) {System.out.println(e);}

        meepMeep.setBackground(img)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}