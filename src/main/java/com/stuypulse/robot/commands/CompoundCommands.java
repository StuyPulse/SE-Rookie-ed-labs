/********************** PROJECT LIFT LAB **********************/
/* Copyright (c) 2026 StuyPulse Robotics. All rights reserved.*/
/* This work is licensed under the terms of the MIT license.  */
/**************************************************************/
package com.stuypulse.robot.commands;

import com.stuypulse.robot.subsystems.claw.Claw;
import com.stuypulse.robot.subsystems.elevator.Elevator;
import com.stuypulse.robot.subsystems.swerve.Swerve;

import org.wpilib.command3.Command;

public interface CompoundCommands {
    public static Command alignToScoreScale(
            final Swerve drive, final Elevator elevator, final Claw claw) {
        return Command.noRequirements(
                        coroutine -> {
                            // TODO: Fill this out using the necessary commands!
                        })
                .named("AlignToScoreScale");
    }

    public static Command alignToScoreSwitch(
            final Swerve drive, final Elevator elevator, final Claw claw) {
        return Command.noRequirements(
                        coroutine -> {
                            // TODO: Fill this out using the necessary commands!
                        })
                .named("AlignToScoreSwitch");
    }
}
