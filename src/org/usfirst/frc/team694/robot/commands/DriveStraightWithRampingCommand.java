package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveStraightWithRampingCommand extends PIDCommand {
	double distance;
	double speed;
	double output;
	double startEncoderValue;
	public DriveStraightWithRampingCommand(double distance, double speed) {
		super(0,0,0);
		requires(Robot.drivetrain);
		this.distance = distance;
		this.speed = speed;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.drivetrain.resetGyro();
		//Robot.drivetrain.resetEncoders();
		startEncoderValue = Robot.drivetrain.getEncoderDistance();
		this.getPIDController().setPID(
				SmartDashboard.getNumber("RotateDegreesPID P", 0), 
				SmartDashboard.getNumber("RotateDegreesPID I", 0), 
				SmartDashboard.getNumber("RotateDegreesPID D", 0)
				);
		
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		System.out.println("[RotateDegreesPIDCommand] angle:" + returnPIDInput());
		System.out.println("[RotateDegreesPIDCommand] distance:" + Robot.drivetrain.getLeftEncoderDistance());
		System.out.println("[RotateDegreesPIDCommand] distance:" + Robot.drivetrain.getRightEncoderDistance());
		SmartDashboard.putNumber("Distance", Robot.drivetrain.getEncoderDistance());
		SmartDashboard.putNumber("Angle", returnPIDInput());
		
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.drivetrain.getEncoderDistance() >= distance + startEncoderValue;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.drivetrain.tankDrive(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}

	@Override
	protected double returnPIDInput() {
		return Robot.drivetrain.getGyroAngle();
	}
	@Override
	protected void usePIDOutput(double output) {
		this.output = output;
		if (!isFinished()) {
			Robot.drivetrain.tankDrive(speed + output , speed - output);
		}
	}
}
