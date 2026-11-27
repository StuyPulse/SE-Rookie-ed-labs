/********************** PROJECT LIFT LAB **********************/
/* Copyright (c) 2026 StuyPulse Robotics. All rights reserved.*/
/* This work is licensed under the terms of the MIT license.  */
/**************************************************************/
package com.stuypulse.robot.constants;

import static org.wpilib.units.Units.*;

import com.stuypulse.robot.Robot;

import org.wpilib.fields.Field;
import org.wpilib.fields.Fields;
import org.wpilib.math.geometry.Pose2d;
import org.wpilib.math.geometry.Pose3d;
import org.wpilib.math.geometry.Rotation2d;
import org.wpilib.math.geometry.Rotation3d;
import org.wpilib.smartdashboard.Field2d;
import org.wpilib.units.measure.Distance;

import com.pathplanner.lib.util.FlippingUtil;
import java.util.List;

public interface FieldConstants {
    Field FIELD_LAYOUT = Fields.DEFAULT_FIELD.loadField();
    Field2d FIELD_2D = new Field2d();

    public static Pose2d transformToOppositeAlliance(Pose2d pose) {
        return FlippingUtil.flipFieldPose(pose);
    }

    public static List<Pose2d> transformToOppositeAlliance(List<Pose2d> poses) {
        return poses.stream().map(FieldConstants::transformToOppositeAlliance).toList();
    }

    Distance WIDTH = Meters.of(FIELD_LAYOUT.getFieldWidth());
    Distance LENGTH = Meters.of(FIELD_LAYOUT.getFieldLength());

    Pose2d LEFT_SCALE_CENTER = new Pose2d(Inches.of(182.11), WIDTH.div(2), Rotation2d.ZERO);
    Pose2d RIGHT_SCALE_CENTER =
            new Pose2d(Inches.of(205.6), WIDTH.div(2).plus(Inches.of(47 / 2.0)), Rotation2d.ZERO);
    Distance SCALE_WIDTH_LENGTH = Inches.of(41.7 / 2.0);

    Pose2d LEFT_SWITCH_CENTER = new Pose2d(Inches.of(42.0), Inches.of(147.47), new Rotation2d());
    Pose2d RIGHT_SWITCH_CENTER =
            new Pose2d(Inches.of(42.0), Inches.of(147.47 - 23.5 - 10), new Rotation2d());
    Distance SWITCH_WIDTH_LENGTH = Inches.of(6.7);

    Pose3d STAGING_POSE = new Pose3d(10, 10, 10, Rotation3d.ZERO);

    public static Target getScaleTarget() {
        return Robot.isBlue() ? Target.LEFT_SCALE : Target.RIGHT_SCALE; // not actual method
    }

    public static Target getSwitchTarget() {
        return Robot.isBlue() ? Target.LEFT_SWITCH : Target.RIGHT_SWITCH; // not actual method
    }

    enum Target {
        LEFT_SCALE(LEFT_SCALE_CENTER),
        RIGHT_SCALE(RIGHT_SCALE_CENTER),
        LEFT_SWITCH(LEFT_SWITCH_CENTER),
        RIGHT_SWITCH(RIGHT_SWITCH_CENTER);

        private final Pose2d pose;

        private Target(final Pose2d pose) {
            this.pose = pose;
        }

        public Pose2d getPose() {
            return pose;
        }
    }
}
