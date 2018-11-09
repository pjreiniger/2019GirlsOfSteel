package org.usfirst.frc.team3504.robot.subsystems;

import org.usfirst.frc.team3504.robot.RobotMap;
import org.usfirst.frc.team3504.robot.commands.DriveByArcade;

import com.ctre.phoenix.motorcontrol.FollowerType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Chassis extends Subsystem {

	  private final WPI_TalonSRX talonSRX1 = RobotMap.chassisTalonSRX1;
	  private final WPI_TalonSRX talonSRX2 = RobotMap.chassisTalonSRX2;
	  private final WPI_TalonSRX talonSRX3 = RobotMap.chassisTalonSRX3;
	  private final WPI_TalonSRX talonSRX4 = RobotMap.chassisTalonSRX4;
	  private final WPI_TalonSRX talonSRX5 = RobotMap.chassisTalonSRX5;
	  private final WPI_TalonSRX talonSRX6 = RobotMap.chassisTalonSRX6;
	    
	    private DifferentialDrive drive;
	    private WPI_TalonSRX masterLeft = talonSRX4;
	    private WPI_TalonSRX slaveLeft_A = talonSRX5;
	    private WPI_TalonSRX slaveLeft_B = talonSRX6;
	    private WPI_TalonSRX masterRight = talonSRX1;
	    private WPI_TalonSRX slaveRight_A = talonSRX2;
	    private WPI_TalonSRX slaveRight_B = talonSRX3;


	    public Chassis() {
	    	//This is the constructor
	    	
	    	//Safety and brakes ----------------------------------
	    	talonSRX1.setNeutralMode(NeutralMode.Brake);
	    	talonSRX2.setNeutralMode(NeutralMode.Brake);
	    	talonSRX3.setNeutralMode(NeutralMode.Brake);
	    	
	    	talonSRX4.setNeutralMode(NeutralMode.Brake);
	    	talonSRX5.setNeutralMode(NeutralMode.Brake);
	    	talonSRX6.setNeutralMode(NeutralMode.Brake);
	    	
//	    	talonSRX1.setSafetyEnabled(false);
//	    	talonSRX2.setSafetyEnabled(false);
//	    	talonSRX3.setSafetyEnabled(false);
//	    	
//	    	talonSRX4.setSafetyEnabled(false);
//	    	talonSRX5.setSafetyEnabled(false);
//	    	talonSRX6.setSafetyEnabled(false);
	    	//-----------------------------------------------------
	    	masterLeft.setNeutralMode(NeutralMode.Brake);
	    	slaveLeft_A.setNeutralMode(NeutralMode.Brake);
	    	slaveLeft_B.setNeutralMode(NeutralMode.Brake);

	    	masterRight.setNeutralMode(NeutralMode.Brake);
	    	slaveRight_A.setNeutralMode(NeutralMode.Brake);
	    	slaveRight_B.setNeutralMode(NeutralMode.Brake);

	    	drive = new DifferentialDrive(masterLeft, masterRight);
	    	drive.setSafetyEnabled(true);
	    	drive.setExpiration(0.1);
	    	drive.setMaxOutput(1.0);

	    	//reverse();
	    	
	    	setFollowerMode();
	    	
	    	//invert(true); //uncomment if needed
	    	//if needed to invert the talons, do before putting into the drive
	    	drive = new DifferentialDrive(talonSRX4, talonSRX1);
	    	
	    	drive.setSafetyEnabled(false);
	    	//this line is to set it so the joystick isn't too sensitive to input
	    	drive.setDeadband(0.02);
	    }

	    public void driveByJoystick(double yDir, double xDir){
	    	SmartDashboard.putString("driveByJoystick?", yDir + "," + xDir);
	    	drive.arcadeDrive(yDir,xDir);
	    }

	    public void driveForward() {
	    	masterLeft.set(0.4);
	    	masterRight.set(0.4);
	    }
	    @Override
	    public void initDefaultCommand() {
	        setDefaultCommand(new DriveByArcade());

	        // Set the default command for a subsystem here.
	        // setDefaultCommand(new MySpecialCommand());
	    }

	    @Override
	    public void periodic() {
	    	//if you had something that needed to executed on a periodic sort of time
	    	//instead of tiem, this is where you would put the commands
	    }
	    
	    public void driveByTank(double left, double right) {   
	    	drive.tankDrive(left, right);
	    }
	    
	    public void driveByArcade(double mag, double rotate) {
	    	// speed and how tight the rotation is [-1,1]
	    	drive.arcadeDrive(mag, rotate);
	    }
	    
	    public void invert(boolean x) {
	    	//if the moters are spinning in opposite directions
	    	talonSRX1.setInverted(false);
	    	talonSRX2.setInverted(false);
	    	talonSRX3.setInverted(false);
	    	
	    	talonSRX4.setInverted(x);
	    	talonSRX5.setInverted(x);
	    	talonSRX6.setInverted(x);
	    }
	    
	    public void reverse() {
	    	//if the motors are together but completely reversed (do not pair with invert)
	    	talonSRX1.setInverted(true);
	    	talonSRX3.setInverted(true);
	    	talonSRX5.setInverted(true);
	    	
	    	talonSRX2.setInverted(true);
	    	talonSRX4.setInverted(true);
	    	talonSRX6.setInverted(true);
	    }
	    
	    public void setFollowerMode() {
	    	//follower code
	    	talonSRX2.follow(talonSRX1, FollowerType.PercentOutput);
	    	talonSRX3.follow(talonSRX1, FollowerType.PercentOutput);
	    	
	    	talonSRX5.follow(talonSRX4, FollowerType.PercentOutput);
	    	talonSRX6.follow(talonSRX4, FollowerType.PercentOutput);
	    }
	    
	    public void stop() {
	    	drive.stopMotor();
	    }
}
