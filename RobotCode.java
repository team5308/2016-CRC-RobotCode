package org.usfirst.frc.team5308.robot;

/**
 *  Robot code of FRC TEAM 5308 in 2016 CRC
 *  CREATER: Zhuowei ZHANG
 */

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	int AO1,AO2,AO3;
	
	RobotDrive myRobot;
	Joystick stick,stick2;
	int autoLoopCounter;
	Talon Intake, Reset, Trigger, Shooter;
	Compressor com;
	Solenoid speedswitch,intakelift;
	
	Boolean hiA,hiB;
	
	DigitalInput limitSwitch;
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	
    	AO1 = 1;
    	AO2 = 1;
    	AO3 = 1;
    	
    	//myRobot = new RobotDrive(0,1);
    	com = new Compressor(0);
        Intake = new Talon(9);
        Shooter = new Talon(8);
        Reset = new Talon(6);
        Trigger = new Talon(7);
        
        speedswitch = new Solenoid(2);
        intakelift = new Solenoid(7);
    	
    	RobotDrive myRobot = new RobotDrive(0,1,2,3);

    	stick = new Joystick(0);
    	stick2 = new Joystick(1);
    	
    	com.setClosedLoopControl(true);
    	
    	limitSwitch = new DigitalInput(0);
    	
    }
    
    /**
     * This function is run once each time the robot enters autonomous mode
     */
    public void autonomousInit() {
    	autoLoopCounter = 0;
    	
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	//limit switch
    	
    	if(limitSwitch.get()){
    		myRobot.arcadeDrive(0.5, 0);
    	}
    	else{
    		Shooter.set(-1);
    		Timer.delay(1);
    		Trigger.set(-1);
    		Timer.delay(0.5);
    		Shooter.set(0);
    		Trigger.set(0);
    	}

    	
    }
    
    /**
     * This function is called once each time the robot enters tele-operated mode
     */
    public void teleopInit(){
    	
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        double x = stick.getX();
        double y = stick.getY();
    	
    	myRobot.arcadeDrive(y,-x);

    	if(stick.getRawButton(1)||stick2.getRawButton(1)){
    		Intake.set(1);
    	}
    	else if(stick.getRawButton(2)||stick2.getRawButton(2)){
    		Intake.set(-1);
    	}
    	else{
    		Intake.set(0);
    	}
    	
    	if(stick2.getRawButton(7)){
    		Reset.set(1);
    	}
    	else if(stick2.getRawButton(8)){
    		Reset.set(-1);
    	}
    	else{
    		Reset.set(0);
    	}
    	
    	if(stick2.getRawButton(9)){
    		Trigger.set(1);
    	}
    	else if(stick2.getRawButton(10)){
    		Trigger.set(-1);
    	}
    	else{
    		Trigger.set(0);
    	}
    	
    	if(stick2.getRawButton(11)){
    		Shooter.set(1);
    	}
    	else if(stick2.getRawButton(12)){
    		Shooter.set(-1);
    	}
    	else{
    		Shooter.set(0);
    	}
    	
    	
    	if(stick.getRawButton(5)&&AO2>30){
    		intakelift.set(true);
    		AO2=1;
    	}
    	else if(stick.getRawButton(3)&&AO2>40){
    		intakelift.set(false);
    		AO2 =1;
    	}
    	AO2++;
    	
    	if(stick.getRawButton(4)&&AO3>30){
    		speedswitch.set(true);
    		AO3 = 1;
    	}
    	else if(stick.getRawButton(6)&&AO3>40){
    		speedswitch.set(false);
    		AO3 = 1;
    	}
    	AO3++;
    	
    	
    }
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic(){
    	
    }
    
}

