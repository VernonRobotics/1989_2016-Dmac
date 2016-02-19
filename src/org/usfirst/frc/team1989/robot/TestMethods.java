package org.usfirst.frc.team1989.robot;

import edu.wpi.first.wpilibj.CANTalon;

public class TestMethods {
	// Quick method to easily set the value of the motors in the test method
	CANTalon frontleftmotor = new CANTalon(3);
	CANTalon frontrightmotor = new CANTalon(9);
	CANTalon backleftmotor = new CANTalon(2);
	CANTalon backrightmotor = new CANTalon(3);
	
	public void setMotors(double vValue){
	    frontrightmotor.set(vValue);
		frontleftmotor.set(vValue);
		backrightmotor.set(vValue);
		backleftmotor.set(vValue);
	}
}
