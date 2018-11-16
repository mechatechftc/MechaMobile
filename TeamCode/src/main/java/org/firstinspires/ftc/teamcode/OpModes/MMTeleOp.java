package org.firstinspires.ftc.teamcode.OpModes;

import com.edinaftc.ninevolt.core.hw.Hardware;
import com.edinaftc.ninevolt.core.hw.drivetrain.Movement;
import com.edinaftc.ninevolt.util.ExceptionHandling;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Functions.MMArm;
import org.firstinspires.ftc.teamcode.MMRobot;

/**
 * Created by Nickolas Idrogo-Lam and Eric Seng 9/27/18
 */
@TeleOp(name = "TeleOp Drive 1", group = "real")
@Disabled
public class MMTeleOp extends OpMode {

    private MMRobot robot;
    private MMArm arm;
    private Movement movement;
    private Hardware hardware;
    private int block;
    private double lastBlockUpdateTime;


    // Initialization block - run once
    @Override
    public void init() {
        try {
            // Actually initialize robot
            robot = new MMRobot(this);

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

    public void armControlRaising() {
        if (gamepad2.right_bumper) arm.raiseArm(1);
        else if (gamepad2.left_bumper) arm.lowerArm(-1);
    }

    public void armControlExtending() {
        if (gamepad2.dpad_up) arm.extendArm(1);
        if (gamepad2.dpad_down) arm.retractArm(-1);
    }

    public void servoController(){
        if (gamepad2.right_trigger > 0) arm.intake();
    }

    public float softGear() {
        if (gamepad1.right_bumper) {
            return 0.5f;
        } else {
            return 1f;
        }
    }

    //Let the user know when TeleOp has ended
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