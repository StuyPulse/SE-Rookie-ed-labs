/************************ LAB - KRAKEN ************************/
/* Copyright (c) 2026 StuyPulse Robotics. All rights reserved.*/
/* This work is licensed under the terms of the MIT license.  */
/**************************************************************/
package com.stuypulse.robot.subsystems.intake;

//PART 3:
//make the abstract class named IntakeIOBase
    //it has IntakeIO inside of its name? wow! what do you think this abstract class should implement? - do it :D

    /*
     make the LoggedTalonFX object. We will initialize this in the constructor. src\main\java\com\stuypulse\robot\util\talonfx\logged\LoggedTalonFX.java
         name this intakeMotor
     make the VoltageOut object. We will initialize this in the constructor. https: api.ctr-electronics.com/phoenix6/stable/java/com/ctre/phoenix6/controls/VoltageOut.html
         name this intakeMotorController

     !!! as a general notice, you should be making fields private final and then initializing in the constructor
    */

    /*
     make the constructor for this class
         this class is meant to configure a passed in motor, helping us avoid repeating code. Therefore, you should make a LoggedTalonFX parameter called intakeMotor
     inside the constructor, you must:
         initialize the LoggedTalonFX motor field you made (use the keyword this!)
         configure the motor with the TalonFXConfig object you made in IntakeConstants.java
         initialize the VoltageOut object
    */

    /*
    Override the updateInputs method
         the IntakeIOInputs parameter should be passed into intakeMotor.updateInputs() -> review the code in the file path attached at the top for LoggedTalonFX
    */

    /*
    Override the updateOutputs method
         it should use a switch case on the IntakeIOOutputs parameter's outputMode field
         account for the RUN and STOP cases
             for STOP call intakeMotor.stop()
             for RUN set the output of the VoltageOut controller, then set this as the control for the motor
                 **myVoltageOutController**.withOutput(outputs.voltage);
                 **myIntakeMotor**.setControl(intakeMotorController);
             notice that we use the field voltage from the IntakeIOOutputs parameter to set up the VoltageOutController
    */
