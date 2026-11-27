/********************** PROJECT LIFT LAB **********************/
/* Copyright (c) 2026 StuyPulse Robotics. All rights reserved.*/
/* This work is licensed under the terms of the MIT license.  */
/**************************************************************/
package com.stuypulse.robot.subsystems.buddyclimb;

import static org.wpilib.units.Units.*;

import com.stuypulse.robot.util.talonfx.logged.TalonFXInputs;

import org.wpilib.units.measure.*;

import org.littletonrobotics.junction.AutoLog;
import org.littletonrobotics.junction.AutoLogOutput;

public interface BuddyIO {
    @AutoLog
    class BuddyClimbInputs {
        public TalonFXInputs winchInputs = new TalonFXInputs();
    }

    enum BuddyClimbOutputMode {
        STOP,
        POSITION_VOLTAGE
    }

    class BuddyClimbOutputs {
        @AutoLogOutput(key = "Buddy Climb/State")
        public BuddyClimbOutputMode outputMode = BuddyClimbOutputMode.STOP;

        @AutoLogOutput(key = "Buddy Climb/Target Position")
        public Angle targetPosition = Rotations.zero();
    }

    default void updateInputs(final BuddyClimbInputs inputs) {}

    default void applyOutputs(final BuddyClimbOutputs outputs) {}
}
