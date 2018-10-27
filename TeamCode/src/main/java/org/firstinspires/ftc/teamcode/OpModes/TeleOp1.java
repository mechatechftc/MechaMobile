package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.MMRobot2;
import org.firstinspires.ftc.teamcode.MotorDirection;


@TeleOp(name="Basic: Iterative OpMode", group="Iterative Opmode")
public class TeleOp1 extends OpMode
{
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private MMRobot2 _robot ;

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        _robot = new MMRobot2(this);

        telemetry.addData("Collector", "Initialized");

        // Tell the driver that initialization is complete.
        telemetry.addData("Status", "Initialized");
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        runtime.reset();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop()
    {
        Drive();
        HangRobot();
        RetractArm();
        RaiseArm();
        Collect();
        UnLock();



        // Show the elapsed game time and wheel power.
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.update();
    }

    void RaiseArm()
    {
        double power = Range.clip(gamepad2.left_stick_y, -0.5, 1.0) ;
        _robot.RaiseArm(power);
        telemetry.addData("Arm Rotate", "Up");
    }


    void RetractArm()
    {
        if(gamepad2.right_trigger > 0.0 && gamepad2.left_trigger > 0.0)
        {
            _robot.ExtendArm(MotorDirection.Off, 0);
            //telemetry.addData("Arm Extend", "Off");
        }
        else if(gamepad2.right_trigger > 0.0)
        {
            double power = Range.clip(gamepad2.right_trigger, 0.0, 1.0) ;
            _robot.ExtendArm(MotorDirection.Forward, power);
            //telemetry.addData("Arm Extend", "Power (%.2f)", power);
        }
        else if(gamepad2.left_trigger > 0.0)
        {
            double power = Range.clip(gamepad2.left_trigger, 0.0, 1.0) ;
            _robot.ExtendArm(MotorDirection.Backward, power);
            //telemetry.addData("Arm Extend", "Power (%.2f)", power);
        }
        else
        {
            _robot.ExtendArm(MotorDirection.Off, 0.0);
            //telemetry.addData("Arm Extend", "Power (%.2f)", 0);
        }
    }

    void HangRobot()
    {
        if(gamepad2.right_bumper)
        {
            _robot.Hang(MotorDirection.Forward);
            telemetry.addData("Hook", "Up");
        }
        else if(gamepad2.left_bumper)
        {
            _robot.Hang(MotorDirection.Backward);
            telemetry.addData("Hook", "Down");
        }
        else
        {
            _robot.Hang(MotorDirection.Off);
            telemetry.addData("Hook", "Off");
        }
    }

    void Drive()
    {
        // Setup a variable for each drive wheel to save power level for telemetry
        double leftPower;
        double rightPower;

        // Choose to drive using either Tank Mode, or POV Mode
        // Comment out the method that's not used.  The default below is POV.

        // POV Mode uses left stick to go forward, and right stick to turn.
        // - This uses basic math to combine motions and is easier to drive straight.

        //double drive = -gamepad1.left_stick_y;
        //double turn  =  gamepad1.right_stick_x;
        //leftPower    = Range.clip(drive + turn, -1.0, 1.0) ;
        //rightPower   = Range.clip(drive - turn, -1.0, 1.0) ;

        // Tank Mode uses one stick to control each wheel.
        // - This requires no math, but it is hard to drive forward slowly and keep straight.

        leftPower  = -gamepad1.left_stick_y ;
        rightPower = -gamepad1.right_stick_y ;

        _robot.Drive(leftPower, rightPower);
        telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
    }

    void Collect()
    {
        if(gamepad2.a)
        {
            _robot.useCollector(MotorDirection.Forward);
            telemetry.addData("Collect", "On");
        }
        if(gamepad2.x)
        {
            _robot.useCollector(MotorDirection.Off);
            telemetry.addData("Collect", "Off");
        }
    }

    public void UnLock()
    {
        if(gamepad2.y) {
            _robot.markerDrop();
            telemetry.addData("MarkerDrop", "Dropping");
        }
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}
