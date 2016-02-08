package org.usfirst.frc.team1989.robot;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class arcadeDriveCmd implements cmd {

	public String type = "arcadeDriveCmd";
	public ArrayList<cmd> list;
	JsScaled driveStick;
	RobotDrive rd;
	
	
	/*
	 * Main controller for use.  Based on 4 motors anda  speed controller.
	 */
	public arcadeDriveCmd(int leftMotorChannel, int rightMotorChannel, JsScaled driveStick) {
		RobotDrive rd = new RobotDrive(leftMotorChannel, rightMotorChannel);
		this.driveStick = driveStick;
	}
		
	// Included for completeness, but unused
	public arcadeDriveCmd(SpeedController leftMotor, SpeedController rightMotor, JsScaled driveStick) {
		RobotDrive rd = new RobotDrive(leftMotor, rightMotor);
		this.driveStick = driveStick;
	}
	public arcadeDriveCmd(int frontLeftMotor, int rearLeftMotor, int frontRightMotor, int rearRightMotor, JsScaled driveStick) {
		RobotDrive rd = new RobotDrive(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
		this.driveStick = driveStick;
	}
	
	public arcadeDriveCmd(SpeedController frontLeftMotor, SpeedController rearLeftMotor, SpeedController frontRightMotor, SpeedController rearRightMotor, JsScaled driveStick) {
		RobotDrive rd = new RobotDrive(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
		this.driveStick = driveStick;
	}

	
	@Override
	public void disabledInit() {
		// TODO Auto-generated method stub

	}

	@Override
	public void autonomousInit() {
		// TODO Auto-generated method stub

	}

	@Override
	public void autonomousPeriodic() {
		rd.arcadeDrive(driveStick.getY(), driveStick.getTwist());
		// TODO Auto-generated method stub

	}

	@Override
	public void DisabledPeriodic() {
		rd.arcadeDrive(0, 0);
		// TODO Auto-generated method stub

	}

	@Override
	public void testInit() {
		// TODO Auto-generated method stub

	}

	@Override
	public void teleopInit() {
		// TODO Auto-generated method stub

	}

	@Override
	public void teleopPeriodic() {
		// TODO Auto-generated method stub
		rd.arcadeDrive(driveStick.sgetY(), driveStick.sgetTwist());
	}

	@Override
	public void testPeriodic() {
		// TODO Auto-generated method stub

	}
}
