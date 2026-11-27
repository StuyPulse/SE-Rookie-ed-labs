/********************** PROJECT LIFT LAB **********************/
/* Copyright (c) 2026 StuyPulse Robotics. All rights reserved.*/
/* This work is licensed under the terms of the MIT license.  */
/**************************************************************/
package com.stuypulse.robot;

import com.stuypulse.robot.commands.CompoundCommands;
import com.stuypulse.robot.commands.DriveCommands;
import com.stuypulse.robot.commands.auton.AutonomousRoutines;
import com.stuypulse.robot.commands.subsystems.ClawCommands;
import com.stuypulse.robot.constants.GlobalPorts;
import com.stuypulse.robot.constants.GlobalSettings;
import com.stuypulse.robot.generated.TunerConstants;
import com.stuypulse.robot.subsystems.buddyclimb.BuddyClimb;
import com.stuypulse.robot.subsystems.buddyclimb.BuddyIO;
import com.stuypulse.robot.subsystems.buddyclimb.BuddyIOReal;
import com.stuypulse.robot.subsystems.buddyclimb.BuddyIOSim;
import com.stuypulse.robot.subsystems.claw.Claw;
import com.stuypulse.robot.subsystems.claw.ClawIO;
import com.stuypulse.robot.subsystems.claw.ClawIOReal;
import com.stuypulse.robot.subsystems.claw.ClawIOSim;
import com.stuypulse.robot.subsystems.elevator.Elevator;
import com.stuypulse.robot.subsystems.elevator.ElevatorIO;
import com.stuypulse.robot.subsystems.elevator.ElevatorIOReal;
import com.stuypulse.robot.subsystems.elevator.ElevatorIOSim;
import com.stuypulse.robot.subsystems.swerve.GyroIO;
import com.stuypulse.robot.subsystems.swerve.GyroIOPigeon2;
import com.stuypulse.robot.subsystems.swerve.ModuleIO;
import com.stuypulse.robot.subsystems.swerve.ModuleIOSim;
import com.stuypulse.robot.subsystems.swerve.ModuleIOTalonFX;
import com.stuypulse.robot.subsystems.swerve.Swerve;
import com.stuypulse.robot.subsystems.vision.Vision;
import com.stuypulse.robot.subsystems.vision.VisionConstants;
import com.stuypulse.robot.subsystems.vision.VisionConstants.Camera;
import com.stuypulse.robot.subsystems.vision.VisionConstants.VisionMode;
import com.stuypulse.robot.subsystems.vision.VisionIO;
import com.stuypulse.robot.subsystems.vision.VisionIOLimelight;
import com.stuypulse.robot.subsystems.vision.VisionIOPhoton;
import com.stuypulse.robot.subsystems.vision.VisionIOPhotonSim;
import com.stuypulse.robot.util.simulation.Simulation;

import org.wpilib.command3.Command;
import org.wpilib.command3.button.CommandNiDsXboxController;

import java.util.EnumMap;
import org.littletonrobotics.junction.networktables.LoggedNetworkChooser;

public class RobotContainer {

    // Gamepads
    public final CommandNiDsXboxController DRIVER =
            new CommandNiDsXboxController(GlobalPorts.Gamepad.DRIVER);
    public final CommandNiDsXboxController OPERATOR =
            new CommandNiDsXboxController(GlobalPorts.Gamepad.OPERATOR);

    // Autons
    private static final LoggedNetworkChooser<Command> AUTON_CHOOSER =
            new LoggedNetworkChooser<>("Auton");

    // Subsystem
    private final Swerve swerve;

    @SuppressWarnings("unused")
    private final BuddyClimb buddyClimb;

    private final Claw claw;
    private final Elevator elevator;

    @SuppressWarnings("unused")
    private final Vision vision;

    // Robot container

    public RobotContainer() {
        switch (GlobalSettings.CURRENT_MODE) {
            case REAL -> {
                swerve =
                        new Swerve(
                                new GyroIOPigeon2(),
                                new ModuleIOTalonFX(TunerConstants.FrontLeft),
                                new ModuleIOTalonFX(TunerConstants.FrontRight),
                                new ModuleIOTalonFX(TunerConstants.BackLeft),
                                new ModuleIOTalonFX(TunerConstants.BackRight));
                buddyClimb = new BuddyClimb(new BuddyIOReal());
                claw = new Claw(new ClawIOReal());
                elevator = new Elevator(new ElevatorIOReal());
            }
            case SIM -> {
                swerve =
                        new Swerve(
                                new GyroIO() {},
                                new ModuleIOSim(TunerConstants.FrontLeft),
                                new ModuleIOSim(TunerConstants.FrontRight),
                                new ModuleIOSim(TunerConstants.BackLeft),
                                new ModuleIOSim(TunerConstants.BackRight));
                buddyClimb = new BuddyClimb(new BuddyIOSim());
                claw = new Claw(new ClawIOSim());
                elevator = new Elevator(new ElevatorIOSim());
                new Simulation(swerve, elevator, claw);
            }
            default -> {
                swerve =
                        new Swerve(
                                new GyroIO() {},
                                new ModuleIO() {},
                                new ModuleIO() {},
                                new ModuleIO() {},
                                new ModuleIO() {});
                buddyClimb = new BuddyClimb(new BuddyIO() {});
                claw = new Claw(new ClawIO() {});
                elevator = new Elevator(new ElevatorIO() {});
            }
        }
        EnumMap<Camera, VisionIO> visionIOMap = new EnumMap<>(Camera.class);
        switch (GlobalSettings.CURRENT_MODE) {
            case REAL -> {
                for (Camera camera : VisionConstants.Camera.values()) {
                    visionIOMap.put(
                            camera,
                            VisionConstants.VISION_MODE == VisionMode.LIMELIGHT
                                    ? new VisionIOLimelight(
                                            camera.getName(),
                                            camera.getRobotToCamera(),
                                            swerve::getRotation)
                                    : new VisionIOPhoton(
                                            camera.getName(), camera.getRobotToCamera()));
                }
            }

            case SIM -> {
                for (Camera camera : Camera.values()) {
                    visionIOMap.put(
                            camera,
                            new VisionIOPhotonSim(
                                    camera.getName(), camera.getRobotToCamera(), swerve::getPose));
                }
            }

            default -> {
                for (Camera camera : Camera.values()) {
                    visionIOMap.put(camera, new VisionIO() {});
                }
            }
        }
        vision = new Vision(swerve.visionConsumer, visionIOMap);

        configureLogging();
        configureDefaultCommands();
        configureButtonBindings();
        configureAutons();
    }

    /***************/
    /*** LOGGING ***/
    /***************/

    private void configureLogging() {}

    /****************/
    /*** DEFAULTS ***/
    /****************/

    private void configureDefaultCommands() {
        swerve.setDefaultCommand(
                DriveCommands.joystickDrive(
                        swerve, DRIVER::getLeftX, DRIVER::getLeftY, DRIVER::getRightX));
    }

    /***************/
    /*** BUTTONS ***/
    /***************/

    private void configureButtonBindings() {
        DRIVER.b().whileTrue(CompoundCommands.alignToScoreScale(swerve, elevator, claw));
        DRIVER.leftBumper().whileTrue(CompoundCommands.alignToScoreSwitch(swerve, elevator, claw));
        DRIVER.x().whileTrue(ClawCommands.outtakeIntakeSide(claw));
        DRIVER.rightBumper().whileTrue(ClawCommands.outtakeElevatorSide(claw));
        DRIVER.a().onTrue(ClawCommands.intake(claw));
    }

    /**************/
    /*** AUTONS ***/
    /**************/

    public void configureAutons() {
        AUTON_CHOOSER.addDefault("Do Nothing", AutonomousRoutines.doNothingAuton());

        // SmartDashboard.putData("Autonomous", AUTON_CHOOSER);
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return The command to run in autonomous
     */
    public Command getAutonomousCommand() {
        return AUTON_CHOOSER.getSelected();
    }
}
