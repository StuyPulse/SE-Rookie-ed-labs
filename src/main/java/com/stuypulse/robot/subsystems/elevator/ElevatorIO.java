/********************** PROJECT LIFT LAB **********************/
/* Copyright (c) 2026 StuyPulse Robotics. All rights reserved.*/
/* This work is licensed under the terms of the MIT license.  */
/**************************************************************/
package com.stuypulse.robot.subsystems.elevator;

import static org.wpilib.units.Units.*;

import com.stuypulse.robot.util.talonfx.logged.TalonFXInputs;

import org.wpilib.units.measure.*;

import org.littletonrobotics.junction.AutoLog;
import org.littletonrobotics.junction.AutoLogOutput;

public interface ElevatorIO {
    @AutoLog
    class ElevatorInputs {
        // top right (leader) motor
        public TalonFXInputs TRMotorInputs = new TalonFXInputs();
        public boolean TRMotorMotionMagicAtTarget = false;
    }

    enum ElevatorOutputMode {
        IDLE,
        MOTION_MAGIC
    }

    class ElevatorOutputs {
        @AutoLogOutput(key = "Elevator/Output Mode")
        ElevatorOutputMode outputMode = ElevatorOutputMode.IDLE;

        @AutoLogOutput(key = "Elevator/Profile Setpoint")
        Angle profileSetpoint = Rotations.zero();
    }

    default void updateInputs(final ElevatorInputs inputs) {}

    default void applyOutputs(final ElevatorOutputs outputs) {}
}
