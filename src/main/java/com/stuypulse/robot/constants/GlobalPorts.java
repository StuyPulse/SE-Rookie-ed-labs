/************************ LAB - KRAKEN ************************/
/* Copyright (c) 2026 StuyPulse Robotics. All rights reserved.*/
/* This work is licensed under the terms of the MIT license.  */
/**************************************************************/
package com.stuypulse.robot.constants;

import com.ctre.phoenix6.CANBus;

/**
 * This file contains the different hardware ports of hardware not belonging to a particular
 * subsystem.
 */
public interface GlobalPorts {
    public interface Gamepad {
        int DRIVER = 0;
        int OPERATOR = 1;
        int DEBUGGER = 2;
    }

    public interface intake {
        CANBus canbus = new CANBus(""); // TODO: make this real value
    }
}
