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
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(62, -34, Math.toRadians(180)))
                                .forward(24)
                                // place purple pixel
                                .turn(Math.toRadians(-90))
                                .forward(78)
                                // place yellow pixel
                                .turn(Math.toRadians(180))
                                .forward(98)
                                // pickup white pixel
                                .turn(Math.toRadians(180))
                                .forward(98)
                                // place white pixel
                                .turn(Math.toRadians(180))
                                .forward(98)
                                // pickup white pixel
                                .turn(Math.toRadians(180))
                                .forward(98)
                                // place white pixel
                                .strafeTo(new Vector2d(60,50))
                                .forward(10)
                                .build()
                );
    }

    public static RoadRunnerBotEntity topRed(MeepMeep meepMeep) {
        return new DefaultBotBuilder(meepMeep)
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(62, 12, Math.toRadians(180)))
                                .forward(24)
                                // place purple pixel
                                .turn(Math.toRadians(-90))
                                .forward(32)
                                // place yellow pixel
                                .turn(Math.toRadians(180))
                                .forward(98)
                                // pickup white pixel
                                .turn(Math.toRadians(180))
                                .forward(98)
                                // place white pixel
                                .turn(Math.toRadians(180))
                                .forward(98)
                                // pickup white pixel
                                .turn(Math.toRadians(180))
                                .forward(98)
                                // place white pixel
                                .strafeTo(new Vector2d(60,50))
                                .forward(10)
                                .build()
                );
    }

    public static RoadRunnerBotEntity bottomBlue(MeepMeep meepMeep) {
        return new DefaultBotBuilder(meepMeep)
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-62, -34, Math.toRadians(0)))
                                .forward(24)
                                // place purple pixel
                                .turn(Math.toRadians(90))
                                .forward(78)
                                // place yellow pixel
                                .turn(Math.toRadians(180))
                                .forward(98)
                                // pickup white pixel
                                .turn(Math.toRadians(180))
                                .forward(98)
                                // place white pixel
                                .turn(Math.toRadians(180))
                                .forward(98)
                                // pickup white pixel
                                .turn(Math.toRadians(180))
                                .forward(98)
                                // place white pixel
                                .strafeTo(new Vector2d(-60,50))
                                .forward(10)
                                .build()
                );
    }

    public static RoadRunnerBotEntity topBlue(MeepMeep meepMeep) {
        return new DefaultBotBuilder(meepMeep)
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-62, 12, Math.toRadians(0)))
                                .forward(24)
                                // place purple pixel
                                .turn(Math.toRadians(90))
                                .forward(32)
                                // place yellow pixel
                                .turn(Math.toRadians(180))
                                .forward(98)
                                // pickup white pixel
                                .turn(Math.toRadians(180))
                                .forward(98)
                                // place white pixel
                                .turn(Math.toRadians(180))
                                .forward(98)
                                // pickup white pixel
                                .turn(Math.toRadians(180))
                                .forward(98)
                                // place white pixel
                                .strafeTo(new Vector2d(-60,50))
                                .forward(10)
                                .build()
                );
    }

    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        // RoadRunnerBotEntity myBot = topBlue(meepMeep);
        // RoadRunnerBotEntity myBot = bottomBlue(meepMeep);
        // RoadRunnerBotEntity myBot = topRed(meepMeep);
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