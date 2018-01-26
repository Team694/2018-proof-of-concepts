package org.usfirst.frc.team694.robot.subsystems;

import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Drivetrain extends Subsystem {

	public WPI_TalonSRX leftFront;
	public WPI_TalonSRX rightFront;
	public WPI_TalonSRX leftRear;
	public WPI_TalonSRX rightRear;
	
	private Encoder rightEncoder;
	private Encoder leftEncoder;

	private ADXRS450_Gyro gyro;

	private DifferentialDrive differentialDrive;

	private SpeedControllerGroup leftSpeedController;
	private SpeedControllerGroup rightSpeedController;

	public Drivetrain() {
		leftFront = new WPI_TalonSRX(4);
		leftRear = new WPI_TalonSRX(3);
		leftSpeedController = new SpeedControllerGroup(leftFront, leftRear);

		rightFront = new WPI_TalonSRX(1);
		rightRear = new WPI_TalonSRX(2);
		rightSpeedController = new SpeedControllerGroup(rightFront, rightRear);

		
		leftFront.setInverted(true);
		leftRear.setInverted(true);
		//rightFront.setInverted(true);
		//rightRear.setInverted(true);
		gyro = new ADXRS450_Gyro();
        gyro.reset();
        gyro.calibrate();
        
		leftFront.setNeutralMode(NeutralMode.Brake);
		leftRear.setNeutralMode(NeutralMode.Brake);
		rightFront.setNeutralMode(NeutralMode.Brake);
		rightRear.setNeutralMode(NeutralMode.Brake);
		differentialDrive = new DifferentialDrive(leftSpeedController, rightSpeedController);

		rightEncoder = new Encoder(2, 3);
		leftEncoder = new Encoder(0, 1);
        leftEncoder.setDistancePerPulse(RobotMap.DRIVETRAIN_ENCODER_INCHES_PER_PULSE);
        rightEncoder.setDistancePerPulse(RobotMap.DRIVETRAIN_ENCODER_INCHES_PER_PULSE);

		
	}
	
	public void motionMagicDrive(double targetPosition) {
		leftFront.set(ControlMode.MotionMagic, targetPosition);
		leftRear.set(ControlMode.MotionMagic, targetPosition);
		rightFront.set(ControlMode.MotionMagic, targetPosition);
		rightRear.set(ControlMode.MotionMagic, targetPosition);
	}
	
	public void tankDrive(double left, double right) {
		leftFront.set(left);
		leftRear.set(left);
		rightFront.set(right);
		rightRear.set(right);
		//FIXME: This doesn't work: differentialDrive.tankDrive(left, right);
	}

	public void resetEncoders() {
		leftEncoder.reset();
		rightEncoder.reset();
	}
	public void resetGyro() {
		gyro.reset();
	}
	public double getGyroAngle() {
		return gyro.getAngle();
	}

	public double getLeftEncoderDistance() {
		return -1 * leftEncoder.getDistance();
//		return -1.0 * (leftEncoder.getDistance() * RobotMap.DRIVETRAIN_ENCODERS_INCHES_PER_REVOLUTION)
//                / RobotMap.DRIVETRAIN_ENCODERS_FACTOR;
	}

	public double getRightEncoderDistance() {
		return -1 * leftEncoder.getDistance();
//		return -1.0 * (rightEncoder.getDistance() * RobotMap.DRIVETRAIN_ENCODERS_INCHES_PER_REVOLUTION)
//                / RobotMap.DRIVETRAIN_ENCODERS_FACTOR;
	}

	public double getEncoderDistance() {
		return Math.max(getLeftEncoderDistance(), getRightEncoderDistance());
	}

	

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

}