package org.usfirst.frc.team1989.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;

public class Shooter implements cmd {

	public CANTalon shootMotor1;
	public CANTalon shootMotor2;
	JsScaled driveStick;
	int TalonID1;
	int TalonID2;
	
	public Shooter(CANTalon shootMotor1,CANTalon shootMotor2,JsScaled driveStick){
		this.shootMotor1 = shootMotor1;
		this.shootMotor2 = shootMotor2;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void DisabledPeriodic() {
			shootMotor1.set(0);
			shootMotor2.set(0);
			
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
		// if neither 3 or 1 is pressed we turn off - this is for test only
		// we need a logic to turn it off too or it shuts off after a while
		//so the shooter needs a timer and a state and some logic depending on state
		if(driveStick.getRawButton(3)){
			shootMotor1.set(0.2);
			shootMotor2.set(-0.2);
		} else if (driveStick.getRawButton(1)) {
			shootMotor1.set(-0.7);
			shootMotor2.set(0.7);
		} else {
			shootMotor1.set(0);
			shootMotor2.set(0);
			
		}
		
	}

	@Override
	public void testPeriodic() {
		// TODO Auto-generated method stub
		
	}
	
	public void pickupBall() {
		if(driveStick.getRawButton(3) == true){
			shootMotor1.set(0.2);
			shootMotor2.set(-0.2);
		}
		
	}
	public void spinWheels(){
		if (driveStick.getRawButton(1)){
			shootMotor1.set(-0.7);
			shootMotor2.set(0.7);
		}
	}
	
}
