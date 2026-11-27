/********************** PROJECT LIFT LAB **********************/
/* Copyright (c) 2026 StuyPulse Robotics. All rights reserved.*/
/* This work is licensed under the terms of the MIT license.  */
/**************************************************************/
package com.stuypulse.robot.commands.subsystems;

import com.stuypulse.robot.subsystems.claw.Claw;

import org.wpilib.command3.Command;

public interface ClawCommands {

    public static Command outtakeIntakeSide(final Claw claw) {
        return claw.run(
                        coroutine -> {
                            // TODO: Fill this out using the necessary commands!
                        })
                .named("OuttakeIntakeSide");
    }

    public static Command outtakeElevatorSide(final Claw claw) {
        return claw.run(
                        coroutine -> {
                            // TODO: Fill this out using the necessary commands!
                        })
                .named("OuttakeElevatorSide");
    }

    public static Command intake(final Claw claw) {
        return claw.run(
                        coroutine -> {
                            // TODO: Fill this out using the necessary commands!
                        })
                .named("IntakeElevatorSide");
    }
}
