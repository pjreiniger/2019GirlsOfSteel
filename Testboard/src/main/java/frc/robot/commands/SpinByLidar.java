package frc.robot.commands;
import frc.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SpinByLidar extends Command {

	public double goalLidar = 5;

	public SpinByLidar() {
		requires(Robot.motor);
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
        Robot.motor.motorGo();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return (Robot.motor.getLidarDistance() <= goalLidar + Robot.motor.LIDAR_TOLERANCE && Robot.motor.getLidarDistance() >= goalLidar - Robot.motor.LIDAR_TOLERANCE);

	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.motor.stop();
	}
}