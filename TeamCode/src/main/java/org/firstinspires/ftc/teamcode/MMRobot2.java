package org.firstinspires.ftc.teamcode;

import android.content.ComponentCallbacks;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.*;

public class MMRobot2
{
    private static final double ArmPower  = 1.0;
    private static final double ExtendPower  = 1.0;
    private static final double HookPower  = 1.0;

    private OpMode _ctx;
    private LinearOpMode _ctxl;
    private DcMotor leftDriveFront = null;
    private DcMotor rightDriveFront = null;
    private DcMotor leftDriveRear = null;
    private DcMotor rightDriveRear = null;
    private DcMotor hangMotor = null;
    private DcMotor armRaise = null;
    private DcMotor armExtend = null;
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
        hangMotor = hardwareMap.get(DcMotor.class, "motorHang");
        armRaise = hardwareMap.get(DcMotor.class, "armRaise");
        armExtend = hardwareMap.get(DcMotor.class, "armExtend");
        markerDrop = hardwareMap.get(Servo.class, "markerDrop");
        collectServo = hardwareMap.get(CRServo.class, "rotationServo");
        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        leftDriveFront.setDirection(DcMotor.Direction.REVERSE);
        rightDriveFront.setDirection(DcMotor.Direction.FORWARD);
        leftDriveRear.setDirection(DcMotor.Direction.REVERSE);
        rightDriveRear.setDirection(DcMotor.Direction.FORWARD);
        hangMotor.setDirection(DcMotor.Direction.FORWARD);
        armExtend.setDirection(DcMotor.Direction.FORWARD);
        collectServo.setDirection(DcMotorSimple.Direction.REVERSE);


        leftDriveFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightDriveFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftDriveRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightDriveRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        hangMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armRaise.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armExtend.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public MMRobot2(LinearOpMode ctxl)
    {
        _ctxl = ctxl;

        HardwareMap hardwareMap = ctxl.hardwareMap;

        leftDriveFront  = hardwareMap.get(DcMotor.class, "motorFL");
        rightDriveFront = hardwareMap.get(DcMotor.class, "motorFR");
        leftDriveRear  = hardwareMap.get(DcMotor.class, "motorBL");
        rightDriveRear = hardwareMap.get(DcMotor.class,"motorBR");
        hangMotor = hardwareMap.get(DcMotor.class, "motorHang");
        armRaise = hardwareMap.get(DcMotor.class, "armRaise");
        armExtend = hardwareMap.get(DcMotor.class, "armExtend");
        markerDrop = hardwareMap.get(Servo.class, "markerDrop");
        collectServo = hardwareMap.get(CRServo.class, "rotationServo");
        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        leftDriveFront.setDirection(DcMotor.Direction.REVERSE);
        rightDriveFront.setDirection(DcMotor.Direction.FORWARD);
        leftDriveRear.setDirection(DcMotor.Direction.REVERSE);
        rightDriveRear.setDirection(DcMotor.Direction.FORWARD);
        hangMotor.setDirection(DcMotor.Direction.FORWARD);
        armExtend.setDirection(DcMotor.Direction.FORWARD);
        collectServo.setDirection(DcMotorSimple.Direction.REVERSE);


        leftDriveFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightDriveFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftDriveRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightDriveRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        hangMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armRaise.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armExtend.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void lowerRobot(){
        hangMotor.setPower(-1.0);
    }

    public void raiseRobot(){
        hangMotor.setPower(1);
    }

    public void RaiseArm(double power)
    {
            armRaise.setPower(-power);
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

    public void useCollector(MotorDirection direction)
    {
        switch (direction)
        {
            case Forward:
                collectServo.setPower(0.55);
                break;
            case Backward:
                collectServo.setPower(-1);
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

    //GETTERS AND SETTERS

    public DcMotor getLeftDriveFront() {
        return leftDriveFront;
    }

    public void setLeftDriveFront(DcMotor leftDriveFront) {
        this.leftDriveFront = leftDriveFront;
    }

    public DcMotor getRightDriveFront() {
        return rightDriveFront;
    }

    public void setRightDriveFront(DcMotor rightDriveFront) {
        this.rightDriveFront = rightDriveFront;
    }

    public DcMotor getLeftDriveRear() {
        return leftDriveRear;
    }

    public void setLeftDriveRear(DcMotor leftDriveRear) {
        this.leftDriveRear = leftDriveRear;
    }

    public DcMotor getRightDriveRear() {
        return rightDriveRear;
    }

    public void setRightDriveRear(DcMotor rightDriveRear) {
        this.rightDriveRear = rightDriveRear;
    }

    public DcMotor getHangMotor() {
        return hangMotor;
    }

    public void setHangMotor(DcMotor hangMotor) {
        this.hangMotor = hangMotor;
    }

}
