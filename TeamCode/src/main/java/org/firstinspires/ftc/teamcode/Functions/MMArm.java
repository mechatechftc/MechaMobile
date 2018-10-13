package org.firstinspires.ftc.teamcode.Functions;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

public class MMArm {

    private OpMode ctx;
    private DcMotor armExtendMotor;
    private DcMotor armLiftMotor;
    private Servo rotationServo;

    public MMArm(DcMotor armExtendMotor, DcMotor armLiftMotor, Servo rotationServo, OpMode ctx) {
        this.armExtendMotor = armExtendMotor;
        this.armLiftMotor = armLiftMotor;
        this.rotationServo = rotationServo;
        armExtendMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armLiftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.ctx = ctx;
    }

    public void intake(){
        rotationServo.setDirection(Servo.Direction.FORWARD);
    }

    public void extendArm(float powerLevel) {
        armExtendMotor.setPower(powerLevel);
        armExtendMotor.setDirection(DcMotor.Direction.FORWARD);
    }

    public void retractArm(float powerLevel) {
        armExtendMotor.setPower(powerLevel);
        armExtendMotor.setDirection(DcMotor.Direction.REVERSE);
    }

    public void raiseArm(float powerLevel) {
        armLiftMotor.setPower(powerLevel);
        armLiftMotor.setDirection(DcMotor.Direction.FORWARD);
    }

    public void lowerArm(float powerLevel) {
        armLiftMotor.setPower(powerLevel);
        armLiftMotor.setDirection(DcMotor.Direction.REVERSE);
    }
}