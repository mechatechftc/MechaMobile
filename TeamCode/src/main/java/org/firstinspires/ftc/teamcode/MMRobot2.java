package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

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
    private Servo lockServo = null;
    private Servo collectServo = null;
git
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
        lockServo = hardwareMap.get(Servo.class, "lockServo");
        collectServo = hardwareMap.get(Servo.class, "rotationServo");

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        leftDriveFront.setDirection(DcMotor.Direction.REVERSE);
        rightDriveFront.setDirection(DcMotor.Direction.FORWARD);
        leftDriveRear.setDirection(DcMotor.Direction.REVERSE);
        rightDriveRear.setDirection(DcMotor.Direction.FORWARD);
        armRaise.setDirection(DcMotor.Direction.FORWARD);
        armExtend.setDirection(DcMotor.Direction.FORWARD);
        hookMotor.setDirection(DcMotor.Direction.FORWARD);
    }

    public void RotateArm(double power)
    {
        armRaise.setPower(power);
    }

    public void ExtendArm(MotorDirection direction, double power)
    {
        if(direction == MotorDirection.Backward)
            power = -power;

        armExtend.setPower(power);
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

    public void Collect(MotorDirection direction)
    {
        switch (direction)
        {
            case Forward:
                collectServo.setPosition(1);
                break;

            case Backward:
             //   hookMotor.setPower(-HookPower);
                break;

            case Off:
                collectServo.setPosition(.5);
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

    public void UnlockServo()
    {
        lockServo.setPosition(1);
    }
}
