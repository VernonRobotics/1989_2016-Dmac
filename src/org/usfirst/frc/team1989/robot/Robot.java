
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

/**
 * The VM is configured to automatically run this class, and to call the
 * 
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */

	double driveramp = 6.0;

	CANTalon frontleftmotor = new CANTalon(3);
	CANTalon frontrightmotor = new CANTalon(9);
	CANTalon backleftmotor = new CANTalon(2);
	CANTalon backrightmotor = new CANTalon(3);
	CANTalon shootmotor1 = new CANTalon(2);
	CANTalon shootmotor2 = new CANTalon(8);
	Timer t1 = new Timer();
	Servo s1 = new Servo(0);

	// JsScaled utilityStick = new JsScaled(1);
	JsScaled driveStick = new JsScaled(0);
	ArcadeDriveCmd aDrive = new ArcadeDriveCmd(frontleftmotor, frontrightmotor, driveStick);
	ArrayList<cmd> cmdlist = new ArrayList<cmd>();
	Joystick js = new Joystick(0);

	RobotDrive drive = new RobotDrive(frontleftmotor, frontrightmotor);
	Shooter shooter = new Shooter(shootmotor1, shootmotor2, driveStick);

	public void robotInit() {
		cmdlist.add(aDrive);
		cmdlist.add(shooter);
		frontleftmotor.enableLimitSwitch(false, false);
		frontrightmotor.enableLimitSwitch(false, false);
		backleftmotor.enableLimitSwitch(false, false);
		backrightmotor.enableLimitSwitch(false, false);
		shootmotor1.enableLimitSwitch(false, false);
		shootmotor2.enableLimitSwitch(false, false);

		frontleftmotor.setVoltageRampRate(driveramp);
		frontrightmotor.setVoltageRampRate(driveramp);
		backleftmotor.setVoltageRampRate(driveramp);
		backrightmotor.setVoltageRampRate(driveramp);

	}

	public void autonomousInit() {
		for (int i = 0; i < cmdlist.size(); i++) {
			cmdlist.get(i).autonomousInit();
		}

	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		for (int i = 0; i < cmdlist.size(); i++) {
			cmdlist.get(i).autonomousPeriodic();
		}

	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		for (int i = 0; i < cmdlist.size(); i++) {
			cmdlist.get(i).teleopPeriodic();
		}
	}

	public void testInit()

	{
		t1.start();
	}

	/**
	 * This function is called periodically during test mode
	 * 
	 */
	public void testPeriodic() {
		drive.arcadeDrive(0 - driveStick.sgetY(), 0 - driveStick.sgetTwist());

		// Show that the buttons work - uses servo - intended for use with the
		// dog leg
		if (driveStick.getRawButton(5) == true) {
			s1.set(0);
		} else if (driveStick.getRawButton(6) == true) {
			s1.set(1);
		}

		
		if (t1.get() > .5) {
			t1.reset();
			t1.start();
			SmartDashboard.putString("DB/String 0", " Left I " + frontleftmotor.getOutputCurrent());
			SmartDashboard.putString("DB/String 5", "right I " + frontrightmotor.getOutputCurrent());
			SmartDashboard.putString("DB/String 1", " Left O " + frontleftmotor.getOutputVoltage());
			SmartDashboard.putString("DB/String 6", "right O " + frontrightmotor.getOutputVoltage());
			SmartDashboard.putString("DB/String 2", " Left V " + frontleftmotor.getBusVoltage());
			SmartDashboard.putString("DB/String 7", "right V " + frontrightmotor.getBusVoltage());
			SmartDashboard.putString("DB/String 3", " Left S " + frontleftmotor.getSpeed());
			SmartDashboard.putString("DB/String 8", "right S " + frontrightmotor.getSpeed());
		}

	}

}