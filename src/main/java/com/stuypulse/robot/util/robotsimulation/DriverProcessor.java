/************************ LAB - OOP #1 ************************/
/* Copyright (c) 2026 StuyPulse Robotics. All rights reserved.*/
/* This work is licensed under the terms of the MIT license.  */
/**************************************************************/
package com.stuypulse.robot.util.robotsimulation;

import com.stuypulse.robot.subsystems.drive.DriveController;
import com.stuypulse.robot.subsystems.drive.Driver;
import com.stuypulse.robot.subsystems.drive.TurnController;

import org.wpilib.math.geometry.Pose2d;
import org.wpilib.math.geometry.Rotation2d;

import java.lang.reflect.Field;

public class DriverProcessor {
    private final Driver driver;
    private DriveController driveController;
    private TurnController turnController;

    public DriverProcessor(Driver driver) {
        this.driver = driver;
        String driveType = DriveController.class.getTypeName();
        Field driveField = null;
        String turnType = TurnController.class.getTypeName();
        Field turnField = null;
        for (Field field : driver.getClass().getDeclaredFields()) {
            if (field.getAnnotatedType().getType().getTypeName().equals(driveType)) {
                driveField = field;
            } else if (field.getAnnotatedType().getType().getTypeName().equals(turnType)) {
                turnField = field;
            }
            ;
        }
        if (driveField == null || turnField == null) {
            IO.println(
                    "[LAB] Could not find a field with the type DriveController or TurnController");
            System.exit(1);
        }
        driveField.setAccessible(true);
        turnField.setAccessible(true);
        try {
            driveController = (DriveController) driveField.get(driver);
            turnController = (TurnController) turnField.get(driver);
        } catch (Exception e) {
            IO.println("[LAB] Could not retrieve a valid drive or turn controller");
            System.exit(1);
        }
    }

    public Pose2d getNextPose() {
        return new Pose2d(
                driveController.getX(),
                driveController.getY(),
                Rotation2d.fromDegrees(turnController.getAngleDegrees()));
    }

    public void advance() {
        driver.periodic();
    }
}
