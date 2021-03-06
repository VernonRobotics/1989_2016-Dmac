
package org.usfirst.frc.team1989.robot;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	// This code is run when the robot is first initialized
	
	// This is the ramp for the drive motors
	double driveramp = 6.0;

	// These are the motor objects and the timer
	CANTalon frontLeftMotor = new CANTalon(3);
	CANTalon frontRightMotor = new CANTalon(9);
	CANTalon rearLeftMotor = new CANTalon(6);
	CANTalon rearRightMotor = new CANTalon(7);
	CANTalon shootMotorLeft = new CANTalon(4);
	CANTalon shootMotorRight = new CANTalon(8);
	CANTalon elevator = new CANTalon(5);
	Timer t1 = new Timer();
	Servo s1 = new Servo(0);

	// For the test method
	double i = 0;
	Timer t2 = new Timer();
	
	//For the cCheck Method
	Timer t3 = new Timer();
	double smv1;
	double smv2;
	
    // JsScaled utilityStick = new JsScaled(1);  // Second Joystick?
	JsScaled driveStick = new JsScaled(0);
	ArcadeDriveCmd aDrive = new ArcadeDriveCmd(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor, driveStick);
	ArrayList<cmd> cmdlist = new ArrayList<cmd>();
	Joystick js = new Joystick(0);

	Shooter shooter = new Shooter(shootMotorLeft, shootMotorRight, driveStick);

	
	// This code is run when the robot is initialized
	public void robotInit() {
		System.out.println("Robot more initialized!");
		
		cmdlist.add(aDrive);
		cmdlist.add(shooter);
		
		// Enable or disable the limit switches
		frontLeftMotor.enableLimitSwitch(false, false);
		frontRightMotor.enableLimitSwitch(false, false);
		rearLeftMotor.enableLimitSwitch(false, false);
		rearRightMotor.enableLimitSwitch(false, false);
		shootMotorLeft.enableLimitSwitch(false, false);
		shootMotorRight.enableLimitSwitch(false, false);

		// Set the ramp to the above number
		frontLeftMotor.setVoltageRampRate(driveramp);
		frontRightMotor.setVoltageRampRate(driveramp);
		rearLeftMotor.setVoltageRampRate(driveramp);
		rearRightMotor.setVoltageRampRate(driveramp);

	}

	// This code is ran when autonomous is initialized
	public void autonomousInit() {
		System.out.println("Autonomous initialized!");
		for (int i = 0; i < cmdlist.size(); i++) {
			cmdlist.get(i).autonomousInit();
		}
	}

	// This code is ran periodically during autonomous (About every 50 ms)
	public void autonomousPeriodic() {
		
		for (int i = 0; i < cmdlist.size(); i++) {
			cmdlist.get(i).autonomousPeriodic();
		}
	}
	
	
	
	// Ran periodically during teleop (normal) operation (about every 50 ms)
	public void teleopPeriodic() {
		for (int i = 0; i < cmdlist.size(); i++) {
			cmdlist.get(i).teleopPeriodic();
		}
	}

	
	
	
	// Ran when test mode is initialized
	public void testInit() {
		System.out.println("Test mode initialized!");
		t1.start();
		t3.start();
	}

	
	
	// Ran periodically during test mode (about every 50 ms)
	public void testPeriodic() {
		this.cCheck();
		
		// Elevator code                              
		if(driveStick.getRawButton(3) == true){
			elevator.set(.2);
		} else if (driveStick.getRawButton(4)) {
			elevator.set(-.2);
		} else {
			elevator.set(0);
		}				
		
	 // aDrive.arcadeDriveC(0 - driveStick.sgetY(), 0 - driveStick.sgetTwist());
	
		// Show that the buttons work - uses servo - intended for use with the
		// dog leg
		if (driveStick.getRawButton(5) == true) {
			s1.set(0);
		} else if (driveStick.getRawButton(6) == true) {
			s1.set(1);
		}

		// Output the Voltages, Currents, and Speeds of the motors.
		if (t1.get() > .5) {
			t1.reset();
			t1.start();
			SmartDashboard.putString("DB/String 0", " Left I " + frontLeftMotor.getOutputCurrent());
			SmartDashboard.putString("DB/String 5", "right I " + frontRightMotor.getOutputCurrent());
			SmartDashboard.putString("DB/String 1", " Left O " + frontLeftMotor.getOutputVoltage());
			SmartDashboard.putString("DB/String 6", "right O " + frontRightMotor.getOutputVoltage());
			SmartDashboard.putString("DB/String 2", " Left V " + frontLeftMotor.getBusVoltage());
			SmartDashboard.putString("DB/String 7", "right V " + frontRightMotor.getBusVoltage());
			SmartDashboard.putString("DB/String 3", " Left S " + frontLeftMotor.getSpeed());
			SmartDashboard.putString("DB/String 8", "right S " + frontRightMotor.getSpeed());
		}
		
		// If button 14 is pressed, start the timer and start the motors at one tenth i.
		if(driveStick.getRawButton(14)) {
			t2.start();
			this.setMotors(i * .1); // sets the four motors to one tenth of the value of i (.1 for 1, .2 for 2, 1 for 10)
		}

		// If 5 seconds have passed, turn off the motors, increment i, and reset the timer.
		if(t2.hasPeriodPassed(5)) {
			t2.stop();
			t2.reset();
			this.setMotors(0);
			int i = 0;
			if(i < 70) {  i++; }}

	}
	
	// This method helps simplify setting all the motors to one value.
	public void setMotors(double vValue) {
	    frontRightMotor.set(vValue);
		frontLeftMotor.set(vValue);
		rearRightMotor.set(vValue);
		rearLeftMotor.set(vValue);
	}

	// Checks the current for the shooter motors and 
	public void cCheck() {
		if(t3.hasPeriodPassed(.25)) {
			t3.reset();
			t3.start();
			smv2 = smv1;
			smv1 = shootMotorLeft.getOutputCurrent();
			if(smv1 > smv2 + .25) {
				SmartDashboard.putString("DB/String9", "Preparing to shoot...");
			} else if(smv1 < smv2 - .25) {
				SmartDashboard.putString("DB/String9", "Nearly Ready...");
			} else if (smv1 >= smv2 - .25 || smv1 <= smv2 + .25) {
				SmartDashboard.putBoolean("DB/LED0", true);
				SmartDashboard.putString("DB/String9", "Ready to shoot!");
			}
		}
	}

	public void disabledPeriodic() {
		elevator.set(0);
		this.setMotors(0);
		s1.set(0);
	}

}