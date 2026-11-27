/********************** PROJECT LIFT LAB **********************/
/* Copyright (c) 2026 StuyPulse Robotics. All rights reserved.*/
/* This work is licensed under the terms of the MIT license.  */
/**************************************************************/
package com.stuypulse.robot.subsystems.elevator;

import com.stuypulse.robot.subsystems.elevator.ElevatorConstants.ElevatorConfigs;
import com.stuypulse.robot.subsystems.elevator.ElevatorConstants.ElevatorSettings;
import com.stuypulse.robot.util.talonfx.logged.LoggedTalonFX;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.MotionMagicExpoVoltage;
import com.ctre.phoenix6.signals.MotorAlignmentValue;

// intentioanlly package private
abstract class ElevatorIOBase implements ElevatorIO {
    private final LoggedTalonFX TRMotor; // top right (leader)
    private final LoggedTalonFX BRMotor; // bottom right
    private final LoggedTalonFX BLMotor; // bottom left
    private final LoggedTalonFX TLMotor; // top left

    private final MotionMagicExpoVoltage motionProfileController;
    private final Follower rightFollowerController;
    private final Follower leftFollowerController;

    private final StatusSignal<Boolean> TRMotorMotionMagicAtTarget;

    protected ElevatorIOBase(
            final LoggedTalonFX TRMotor,
            final LoggedTalonFX BRMotor,
            final LoggedTalonFX BLMotor,
            final LoggedTalonFX TLMotor) {
        this.TRMotor = TRMotor;
        this.BRMotor = BRMotor;
        this.BLMotor = BLMotor;
        this.TLMotor = TLMotor;

        ElevatorConfigs.TR_GEARBOX_MOTOR_CONFIG.configure(this.TRMotor);
        ElevatorConfigs.TR_GEARBOX_MOTOR_CONFIG.configure(this.BRMotor);
        ElevatorConfigs.TR_GEARBOX_MOTOR_CONFIG.configure(this.BLMotor);
        ElevatorConfigs.TR_GEARBOX_MOTOR_CONFIG.configure(this.TLMotor);

        this.motionProfileController = new MotionMagicExpoVoltage(ElevatorSettings.STOWED_ANGLE);
        this.rightFollowerController =
                new Follower(this.TRMotor.getDeviceID(), MotorAlignmentValue.Aligned);
        this.leftFollowerController =
                new Follower(this.TRMotor.getDeviceID(), MotorAlignmentValue.Opposed);

        this.BRMotor.setControl(rightFollowerController);
        this.TLMotor.setControl(leftFollowerController);
        this.BLMotor.setControl(leftFollowerController);

        this.TRMotorMotionMagicAtTarget = this.TRMotor.getMotionMagicAtTarget();
    }

    @Override
    public void updateInputs(final ElevatorInputs inputs) {
        TRMotor.updateInputs(inputs.TRMotorInputs);
        TRMotorMotionMagicAtTarget.refresh();
        inputs.TRMotorMotionMagicAtTarget = TRMotorMotionMagicAtTarget.getValue();
    }

    @Override
    public void applyOutputs(final ElevatorOutputs outputs) {
        switch (outputs.outputMode) {
            case IDLE -> {
                this.TRMotor.stopMotor();

                this.BRMotor.setControl(rightFollowerController);
                this.TLMotor.setControl(leftFollowerController);
                this.BLMotor.setControl(leftFollowerController);
            }
            case MOTION_MAGIC ->
                    this.TRMotor.setControl(
                            motionProfileController.withPosition(outputs.profileSetpoint));
        }
        ;
    }
}
