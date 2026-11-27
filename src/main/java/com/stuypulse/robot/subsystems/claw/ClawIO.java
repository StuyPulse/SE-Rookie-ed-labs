/********************** PROJECT LIFT LAB **********************/
/* Copyright (c) 2026 StuyPulse Robotics. All rights reserved.*/
/* This work is licensed under the terms of the MIT license.  */
/**************************************************************/
package com.stuypulse.robot.subsystems.claw;

import static org.wpilib.units.Units.*;

import com.stuypulse.robot.util.talonfx.logged.TalonFXInputs;

import org.wpilib.units.measure.*;

import org.littletonrobotics.junction.AutoLog;
import org.littletonrobotics.junction.AutoLogOutput;

public interface ClawIO {
    @AutoLog
    class ClawInputs {
        public TalonFXInputs rollerMotorInputs = new TalonFXInputs();
        public TalonFXInputs pivotMotorInputs = new TalonFXInputs();
        public boolean pivotMotorMotionMagicAtTarget = false;
    }

    enum PivotOutputMode {
        IDLE,
        MOTION_MAGIC;
    }

    enum RollerOutputMode {
        IDLE,
        DUTY_CYCLE;
    }

    class PivotOutputs {
        @AutoLogOutput(key = "Claw/Pivot/Output Mode")
        public PivotOutputMode pivotOutputMode = PivotOutputMode.IDLE;

        @AutoLogOutput(key = "Claw/Pivot/Profile Setpoint")
        public Angle pivotProfileSetpoint = Rotations.zero();
    }

    class RollerOutputs {
        @AutoLogOutput(key = "Claw/Rollers/Output Mode")
        public RollerOutputMode rollerOutputMode = RollerOutputMode.IDLE;

        @AutoLogOutput(key = "Claw/Rollers/Target Duty Cycle")
        public double rollerTargetDutyCycle = 0.0;
    }

    default void updateInputs(final ClawInputs inputs) {}

    default void applyPivotOutputs(final PivotOutputs outputs) {}

    default void applyRollerOutputs(final RollerOutputs outputs) {}
}
