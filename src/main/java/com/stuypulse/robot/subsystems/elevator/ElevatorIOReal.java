/********************** PROJECT LIFT LAB **********************/
/* Copyright (c) 2026 StuyPulse Robotics. All rights reserved.*/
/* This work is licensed under the terms of the MIT license.  */
/**************************************************************/
package com.stuypulse.robot.subsystems.elevator;

import com.stuypulse.robot.constants.GlobalPorts;
import com.stuypulse.robot.subsystems.elevator.ElevatorConstants.ElevatorPorts;
import com.stuypulse.robot.util.talonfx.logged.LoggedTalonFX;

public final class ElevatorIOReal extends ElevatorIOBase {
    public ElevatorIOReal() {
        super(
                new LoggedTalonFX(ElevatorPorts.TR_MOTOR, GlobalPorts.ELEVATOR),
                new LoggedTalonFX(ElevatorPorts.BR_MOTOR, GlobalPorts.ELEVATOR),
                new LoggedTalonFX(ElevatorPorts.BL_MOTOR, GlobalPorts.ELEVATOR),
                new LoggedTalonFX(ElevatorPorts.TL_MOTOR, GlobalPorts.ELEVATOR));
    }
}
