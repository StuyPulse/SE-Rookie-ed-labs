/********************** PROJECT LIFT LAB **********************/
/* Copyright (c) 2026 StuyPulse Robotics. All rights reserved.*/
/* This work is licensed under the terms of the MIT license.  */
/**************************************************************/
package com.stuypulse.robot.subsystems.buddyclimb;

import com.stuypulse.robot.constants.GlobalPorts;
import com.stuypulse.robot.subsystems.buddyclimb.BuddyClimbConstants.BuddyClimbPorts;
import com.stuypulse.robot.util.talonfx.logged.LoggedTalonFX;

public final class BuddyIOSim extends BuddyIOBase {
    public BuddyIOSim() {
        super(new LoggedTalonFX(BuddyClimbPorts.WINCH_MOTOR, GlobalPorts.BUDDY_CLIMB));
    }
}
