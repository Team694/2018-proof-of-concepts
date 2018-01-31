/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 * An example command.  You can replace me with your own command.
 */
public class DriveCommand extends Command {
	
	private boolean arcadeDrive;
	private boolean wasPressed;
	
	private	double rightTrigger;
	private double leftTrigger;
	
	private static double leftTriggerSquared;
	private static double rightTriggerSquared; 
	
	
	public DriveCommand() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.drivetrain);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		rightTrigger = Robot.oi.driverGamepad.getLeftTriggerAxis();
		leftTrigger = Robot.oi.driverGamepad.getRightTriggerAxis();
		
		rightTriggerSquared = ((rightTrigger + 1) / 2) * ((rightTrigger + 1) / 2);
		leftTriggerSquared = ((leftTrigger + 1) / 2) * ((leftTrigger + 1) / 2);
		
		if(Robot.oi.driverGamepad.getRawButton(1) && wasPressed == false) {
			Robot.drivetrain.toggle();
			wasPressed = Robot.oi.driverGamepad.getRawButton(1);
		}
		Robot.drivetrain.curvatureDrive(Robot.oi.driverGamepad.getRightY(), Robot.oi.driverGamepad.getLeftX());
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
