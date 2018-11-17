package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcontroller.external.samples.HardwarePushbot;
import org.firstinspires.ftc.teamcode.MMRobot2;
import org.firstinspires.ftc.teamcode.MotorDirection;

@Autonomous(name="MechaMobile: AutoDepot", group="Autonomous")
public class AutoDepot extends LinearOpMode {

    //Declare OpMode members.
    MMRobot2 robot;
    private ElapsedTime runtime = new ElapsedTime();

    static final double     COUNTS_PER_MOTOR_REV    = 560 ;    // eg: TETRIX Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 1 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double     DRIVE_SPEED             = 0.6;
    static final double     TURN_SPEED              = 0.5;

    @Override
    public void runOpMode() {

        /*
         * Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
`       */
        robot = new MMRobot2(this);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Resetting Encoders");    //
        telemetry.update();

        robot.getLeftDriveFront().setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.getLeftDriveRear().setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.getRightDriveRear().setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.getRightDriveFront().setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        robot.getLeftDriveFront().setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.getLeftDriveRear().setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.getRightDriveRear().setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.getRightDriveFront().setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Send telemetry message to indicate successful Encoder reset
        telemetry.addData("Path0",  "Starting at %7d :%7d",
                robot.getLeftDriveFront().getCurrentPosition(),
                robot.getLeftDriveRear().getCurrentPosition(),
                robot.getRightDriveFront().getCurrentPosition(),
                robot.getRightDriveRear().getCurrentPosition());
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // Step through each leg of the path,
        // Note: Reverse movement is obtained by setting a negative distance (not speed)
        robot.useCollector(MotorDirection.Forward);
        encoderDrive(DRIVE_SPEED,  72,  72, 5.0);  // S1: Forward 47 Inches with 5 Sec timeout
        robot.markerDrop();


        telemetry.addData("Path", "Complete");
        telemetry.update();
    }

    /*
     *  Method to perfmorm a relative move, based on encoder counts.
     *  Encoders are not reset as the move is based on the current position.
     *  Move will stop if any of three conditions occur:
     *  1) Move gets to the desired position
     *  2) Move runs out of time
     *  3) Driver stops the opmode running.
     */
    public void encoderDrive(double speed,
                             double leftInches, double rightInches,
                             double timeoutS) {
        int newLeftFrontTarget;
        int newLeftRearTarget;
        int newRightFrontTarget;
        int newRightRearTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftFrontTarget = robot.getLeftDriveFront().getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newLeftRearTarget = robot.getLeftDriveRear().getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newRightFrontTarget = robot.getRightDriveFront().getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            newRightRearTarget = robot.getRightDriveRear().getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);

            robot.getLeftDriveFront().setTargetPosition(newLeftFrontTarget);
            robot.getLeftDriveRear().setTargetPosition(newLeftRearTarget);
            robot.getRightDriveFront().setTargetPosition(newRightFrontTarget);
            robot.getRightDriveRear().setTargetPosition(newRightRearTarget);

            // Turn On RUN_TO_POSITION
            robot.getLeftDriveFront().setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.getLeftDriveRear().setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.getRightDriveFront().setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.getRightDriveRear().setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            robot.getLeftDriveFront().setPower(Math.abs(speed));
            robot.getLeftDriveRear().setPower(Math.abs(speed));
            robot.getRightDriveFront().setPower(Math.abs(speed));
            robot.getRightDriveRear().setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (robot.getLeftDriveFront().isBusy() && robot.getLeftDriveRear().isBusy()
                            && robot.getRightDriveFront().isBusy() && robot.getRightDriveRear().isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1",  "Running to %7d :%7d", newLeftFrontTarget,  newRightFrontTarget);
                telemetry.addData("Path2",  "Running at %7d :%7d",
                        robot.getLeftDriveFront().getCurrentPosition(),
                        robot.getLeftDriveRear().getCurrentPosition(),
                        robot.getRightDriveFront().getCurrentPosition(),
                        robot.getRightDriveRear().getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            robot.getLeftDriveFront().setPower(0);
            robot.getLeftDriveRear().setPower(0);
            robot.getRightDriveFront().setPower(0);
            robot.getRightDriveRear().setPower(0);


            // Turn off RUN_TO_POSITION
            robot.getLeftDriveFront().setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.getLeftDriveRear().setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.getRightDriveFront().setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.getRightDriveRear().setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }
}