/************************ PROJECT ZIXI ************************/
/* Copyright (c) 2026 StuyPulse Robotics. All rights reserved.*/
/* This work is licensed under the terms of the MIT license.  */
/**************************************************************/
package com.stuypulse.robot.subsystems.intake;

//PART 5:
//make the subsystem class. It should extend from the PeriodicSubsystem. 

    /*
    declare the following 3 fields
        IntakeIO object called io
        IntakeIOInputsAutoLogged object called inputs
        IntakeIOOutputs object called outputs
    these should all be private final and therefore should be initialized where? >:]
    */


    //Make the constructor. It should take in a parameter of the IntakeIO type
    //it should then initialize..
        //the io field
        //the inputs field
        //and the outputs field
    //be SURE to set the inputs field to new IntakeIOInputsAutoLogged()
    

    //make the IntakeState enum
        //it should have two states, RUN and STOP
        //NOTE: this is a local copy of the state we will be setting our motor to. You will see why as this lab progresses.

    //make a variable that stores the active IntakeState
        //it should have an @AutoLogOutput annotation over it.
        //make sure to set this variable to the default STOP state (private NOT final).

    //create the runIntakeVoltage function that takes in a double parameter, and sets the IntakeIOOutput field's voltage to this parameter
        //also be sure to update the outputs field outputMode to IntakeOutputModes.RUN

    /* 
    make the intakeSetState command
        it should take in an IntakeState
        the focus of this lab is akit, therefore I have provided the definition for you
    public Command intakeSetState(IntakeState state) {
        return run(coroutine -> {
                    this.state = state;
                })
                .named("intake set state to " + state.toString());
    }
    */

    //make the IntakeRun command. It should return the intakeSetState command with the passed in state IntakeState.RUN

    //Override the periodic function
    //This function should update the inputs inside of the io object and log these inputs (use Logger.processInputs())
        //see https://docs.advantagekit.org/data-flow/recording-outputs/
        //optional: https://docs.advantagekit.org/javadoc/org/littletonrobotics/junction/Logger.html
    //this function should also use a switch block to go through the local state and should call the commands we have made 

    //Override the periodicAfterScheduler function
        //it should update the outputs as per the io object
