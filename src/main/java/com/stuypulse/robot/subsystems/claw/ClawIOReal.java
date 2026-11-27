/********************** PROJECT LIFT LAB **********************/
/* Copyright (c) 2026 StuyPulse Robotics. All rights reserved.*/
/* This work is licensed under the terms of the MIT license.  */
/**************************************************************/
package com.stuypulse.robot.subsystems.claw;

import com.stuypulse.robot.constants.GlobalPorts;
import com.stuypulse.robot.subsystems.claw.ClawConstants.ClawPorts;
import com.stuypulse.robot.util.talonfx.logged.LoggedTalonFX;

public final class ClawIOReal extends ClawIOBase {
    public ClawIOReal() {
        super(
                new LoggedTalonFX(ClawPorts.CLAW_PIVOT_MOTOR, GlobalPorts.CLAW),
                new LoggedTalonFX(ClawPorts.CLAW_ROLLER_MOTOR, GlobalPorts.CLAW));
    }
}
