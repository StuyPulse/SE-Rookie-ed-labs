/************************ LAB - KRAKEN ************************/
/* Copyright (c) 2026 StuyPulse Robotics. All rights reserved.*/
/* This work is licensed under the terms of the MIT license.  */
/**************************************************************/
package com.stuypulse.robot.subsystems.intake;

//PART 6
//make the IntakeIOSim class. You should now be familiar with the file structure of akit and know that this should inherit from the IntakeIOBase class

/*
BEFORE STARTING: review the following documentation: 
        https:docs.wpilib.org/en/stable/docs/software/wpilib-tools/robot-simulation/physics-sim.html#wpilib-s-simulation-classes
        https:github.wpilib.org/allwpilib/docs/release/java/edu/wpi/first/wpilibj/simulation/LinearSystemSim.html
        https:github.wpilib.org/allwpilib/docs/release/java/edu/wpi/first/wpilibj/simulation/DCMotorSim.html
*/


        /*
        make a private final field of the SystemSim class. We need to use generics here, so be sure to do: SystemSim<DCMotorSim> name
        make a private final TalonFXSimulation field called simMotor. This is the simulated motor that we will be passing into the parent's constructor
                REVIEW: src/main/java/com/stuypulse/robot/util/talonfx/sim/TalonFXSimulation.java
        */

        /*make the IntakeIOSim constructor
                you should be initializing the SystemSim and the TalonFXSimulation fields
                        recall that the whole purpose of this constructor is to pass a motor to the parent's constructor!
                WARNING: you must restate the class of the objects. This is because this is java's way of making sure you are passing fully defined objects into the parent's constructor.
        further into the structure of this constructor...
                initialize fields
                call the parent's constructor
                !! initialize the fields we have made using this.
        */


        /*
        Override the updateInputs function
                we need to override this because as seen in the first source lists, Simulated objects need to be updated via an update method and refreshed.
                after you implement those calls to update the simulation system, be sure to call super.updateInputs(inputs);
                        check global settings for DT (represents the loop time in the simulation)
        */

