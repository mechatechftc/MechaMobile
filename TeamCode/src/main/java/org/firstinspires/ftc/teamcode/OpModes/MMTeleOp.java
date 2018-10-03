package org.firstinspires.ftc.teamcode.opmodes;

import com.edinaftc.ninevolt.core.hw.Hardware;
import com.edinaftc.ninevolt.core.hw.drivetrain.Movement;
import com.edinaftc.ninevolt.util.ExceptionHandling;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.MMRobot;

/**
 * Created by Nickolas Idrogo-Lam 9/27/18
 */
@TeleOp(name = "PlaceHolder TeleOp", group = "real")
public class MMTeleOp extends OpMode {

    private MMRobot robot;
    private Movement movement;
    private Hardware hardware;
    private int block;
    private double lastBlockUpdateTime;



    // Initialization block - run once
    @Override
    public void init() {
        try {
            // Actually initialize robot
            robot = new HSRobot(this);

            // Utility variables
            hardware = robot.getHardware();
            movement = robot.getMovement();


            movement.setDefaultRunUsingEncoders(true);
            // Alert user that initialization was successful
            telemetry.addData("Initialization", "Done!");
            telemetry.update();


        } catch (Exception e) {
            // Stops OpMode and prints exception in case of exception
            ExceptionHandling.standardExceptionHandling(e, this);
        }
    }

  
  
    // Start block - run on play press - after init, before loop
    @Override
    public void start() {
        // Initalize block timer
        try {
            resetStartTime();
            block = 1;
            lastBlockUpdateTime = 0;
        } catch (Exception e) {
            ExceptionHandling.standardExceptionHandling(e, this);
        }
    }

  
    // Loop block - run repeatedly after init
    @Override
    public void loop() {
        try {
            drive(softGear());
            blockNotify(getRuntime());
        } catch (Exception e) {
            ExceptionHandling.standardExceptionHandling(e, this);
        }

    }


    public void drive(float gearRatio) {
        if (gamepad1.left_trigger != 0) {
            movement.directDrive(-gamepad1.left_trigger * gearRatio, 0, 0);
        } else if (gamepad1.right_trigger != 0) {
            movement.directDrive(gamepad1.right_trigger * gearRatio, 0, 0);
        } else {
            movement.directDrive(
                    0,
                    -gamepad1.left_stick_y * gearRatio,
                    gamepad1.right_stick_x * gearRatio
            );
        }
    }


    public float softGear() {
        if (gamepad1.right_bumper) {
            return 0.5f;
        } else {.
            return 1f;
        }
    }


    public void blockNotify(double rt) {
        telemetry.addData("Block", block);
        if (rt - lastBlockUpdateTime > 10 && block < 12) {
            block++;
            lastBlockUpdateTime =  rt;
        }
        if(rt > 90) {
            telemetry.addLine("End Game");
        }
        telemetry.update();
    }
}
