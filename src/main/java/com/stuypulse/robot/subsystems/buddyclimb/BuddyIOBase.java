/********************** PROJECT LIFT LAB **********************/
/* Copyright (c) 2026 StuyPulse Robotics. All rights reserved.*/
/* This work is licensed under the terms of the MIT license.  */
/**************************************************************/
package com.stuypulse.robot.subsystems.buddyclimb;

import com.stuypulse.robot.subsystems.buddyclimb.BuddyClimbConstants.BuddyClimbConfigs;
import com.stuypulse.robot.subsystems.buddyclimb.BuddyClimbConstants.BuddyClimbSettings;
import com.stuypulse.robot.util.talonfx.logged.LoggedTalonFX;

import com.ctre.phoenix6.controls.PositionVoltage;

// intentionally package private
abstract class BuddyIOBase implements BuddyIO {
    private final LoggedTalonFX winchMotor;

    private final PositionVoltage positionController;

    protected BuddyIOBase(final LoggedTalonFX winchMotor) {
        this.winchMotor = winchMotor;

        BuddyClimbConfigs.WINCH_MOTOR_CONFIG.configure(this.winchMotor);

        this.positionController =
                new PositionVoltage(BuddyClimbSettings.HELD_ANGLE).withEnableFOC(true);
    }

    @Override
    public void updateInputs(final BuddyClimbInputs inputs) {
        winchMotor.updateInputs(inputs.winchInputs);
    }

    @Override
    public void applyOutputs(final BuddyClimbOutputs outputs) {
        switch (outputs.outputMode) {
            case STOP -> this.winchMotor.stopMotor();
            case POSITION_VOLTAGE ->
                    this.winchMotor.setControl(
                            positionController.withPosition(outputs.targetPosition));
        }
        ;
    }
}
