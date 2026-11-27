/********************** PROJECT LIFT LAB **********************/
/* Copyright (c) 2026 StuyPulse Robotics. All rights reserved.*/
/* This work is licensed under the terms of the MIT license.  */
/**************************************************************/
package com.stuypulse.robot.subsystems.claw;

import com.stuypulse.robot.subsystems.claw.ClawConstants.ClawConfigs;
import com.stuypulse.robot.subsystems.claw.ClawConstants.ClawSettings;
import com.stuypulse.robot.util.talonfx.logged.LoggedTalonFX;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.controls.MotionMagicVoltage;

// intentionally package private
abstract class ClawIOBase implements ClawIO {
    private final LoggedTalonFX rollerMotor;
    private final LoggedTalonFX pivotMotor;

    private final MotionMagicVoltage motionProfileController;
    private final DutyCycleOut rollerDutyCycleController;

    private final StatusSignal<Boolean> pivotMotorMotionMagicAtTarget;

    protected ClawIOBase(final LoggedTalonFX rollerMotor, final LoggedTalonFX pivotMotor) {
        this.rollerMotor = rollerMotor;
        this.pivotMotor = pivotMotor;

        ClawConfigs.CLAW_ROLLER_MOTOR_CONFIG.configure(this.rollerMotor);
        ClawConfigs.CLAW_PIVOT_MOTOR_CONFIG.configure(this.pivotMotor);

        this.motionProfileController =
                new MotionMagicVoltage(ClawSettings.Pivot.INTAKE_ANGLE).withEnableFOC(true);
        this.rollerDutyCycleController =
                new DutyCycleOut(ClawSettings.Rollers.IDLE_DUTY_CYCLE).withEnableFOC(true);

        this.pivotMotorMotionMagicAtTarget = pivotMotor.getMotionMagicAtTarget();
    }

    @Override
    public void updateInputs(final ClawInputs inputs) {
        rollerMotor.updateInputs(inputs.rollerMotorInputs);
        pivotMotor.updateInputs(inputs.pivotMotorInputs);
        pivotMotorMotionMagicAtTarget.refresh();
        inputs.pivotMotorMotionMagicAtTarget = pivotMotorMotionMagicAtTarget.getValue();
    }

    @Override
    public void applyPivotOutputs(final PivotOutputs outputs) {
        switch (outputs.pivotOutputMode) {
            case IDLE -> pivotMotor.stopMotor();
            case MOTION_MAGIC ->
                    pivotMotor.setControl(
                            motionProfileController.withPosition(outputs.pivotProfileSetpoint));
        }
        ;
    }

    @Override
    public void applyRollerOutputs(final RollerOutputs outputs) {
        switch (outputs.rollerOutputMode) {
            case IDLE -> rollerMotor.stopMotor();
            case DUTY_CYCLE ->
                    rollerMotor.setControl(
                            rollerDutyCycleController.withOutput(outputs.rollerTargetDutyCycle));
        }
        ;
    }
}
