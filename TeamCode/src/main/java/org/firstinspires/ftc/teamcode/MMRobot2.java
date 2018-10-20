package org.firstinspires.ftc.teamcode;

import android.content.ComponentCallbacks;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.*;

public class MMRobot2
{
    private static final double ArmPower  = 1.0;
    private static final double ExtendPower  = 1.0;
    private static final double HookPower  = 1.0;

    private OpMode _ctx;
    private DcMotor leftDriveFront = null;
    private DcMotor rightDriveFront = null;
    private DcMotor leftDriveRear = null;
    private DcMotor rightDriveRear = null;
    private DcMotor armRaise = null;
    private DcMotor armExtend = null;
    private DcMotor hookMotor = null;
    private Servo markerDrop = null;
    private CRServo collectServo = null;

    public MMRobot2(OpMode ctx)
    {
        _ctx = ctx;

        HardwareMap hardwareMap = ctx.hardwareMap;

        leftDriveFront  = hardwareMap.get(DcMotor.class, "motorFL");
        rightDriveFront = hardwareMap.get(DcMotor.class, "motorFR");
        leftDriveRear  = hardwareMap.get(DcMotor.class, "motorBL");
        rightDriveRear = hardwareMap.get(DcMotor.class,"motorBR");
        armRaise = hardwareMap.get(DcMotor.class, "armRaise");
        armExtend = hardwareMap.get(DcMotor.class, "armExtend");
        hookMotor = hardwareMap.get(DcMotor.class, "hookMotor");
        markerDrop = hardwareMap.get(Servo.class, "markerDrop");
        collectServo = hardwareMap.get(CRServo.class, "rotationServo");
        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        leftDriveFront.setDirection(DcMotor.Direction.REVERSE);
        rightDriveFront.setDirection(DcMotor.Direction.FORWARD);
        leftDriveRear.setDirection(DcMotor.Direction.REVERSE);
        rightDriveRear.setDirection(DcMotor.Direction.FORWARD);
        armExtend.setDirection(DcMotor.Direction.FORWARD);
        hookMotor.setDirection(DcMotor.Direction.FORWARD);
        collectServo.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void RaiseArm(double power)
    {
        if (power > 0){
            armRaise.setDirection(DcMotor.Direction.FORWARD);
            armRaise.setPower(power);
        }
        if (power < 0){
            double newPower = -power;
            armRaise.setDirection(DcMotor.Direction.REVERSE);
            armRaise.setPower(newPower);
        }
        if (power == 0){
            armRaise.setPower(power);
        }
    }

    public void ExtendArm(MotorDirection direction, double power)
    {
        if(direction == MotorDirection.Backward) {
            double newPower = -power;
            armExtend.setPower(newPower);
        }
        else {
            armExtend.setPower(power);
        }
    }

    public void Hang(MotorDirection direction)
    {
        switch (direction)
        {
            case Forward:
                hookMotor.setPower(HookPower);
                break;

            case Backward:
                hookMotor.setPower(-HookPower);
                break;

            case Off:
                hookMotor.setPower(0);
                break;
        }
    }

    public void useCollector(MotorDirection direction)
    {
        switch (direction)
        {
            case Forward:
                collectServo.setPower(0.55);
                break;
            case Off:
                collectServo.setPower(0);
                break;
        }
    }

    public void Drive(double leftPower, double rightPower)
    {
        leftDriveFront.setPower(leftPower);
        leftDriveRear.setPower(leftPower);
        rightDriveFront.setPower(rightPower);
        rightDriveRear.setPower(rightPower);
    }

    public void markerDrop()
    {
        markerDrop.setPosition(1);
    }
}
