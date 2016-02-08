
package org.usfirst.frc.team1989.robot;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
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
	
    JsScaled utilityStick = new JsScaled(0);
    JsScaled driveStick = new JsScaled(1);
    arcadeDriveCmd aDrive = new arcadeDriveCmd(0, 1, driveStick);
    ArrayList<cmd> cmdlist = new ArrayList<cmd>();
    
    
   
   
    
    
    public void robotInit() {
    	cmdlist.add(aDrive);
    	    	
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
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
