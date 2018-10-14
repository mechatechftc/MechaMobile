package org.firstinspires.ftc.teamcode.Functions;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.CRServo;

public class MMCollector {

    private OpMode ctx;
    private CRServo rotationServo;

    public MMCollector(CRServo rotationServo, OpMode ctx) {
        this.rotationServo = rotationServo;
        armExtendMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armLiftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.ctx = ctx;
    }

    public void intake(){
        rotationServo.setDirection(CRServo.Direction.FORWARD);
    }

    public CRServo getRotationServo() {
        return rotationServo;
    }

    public void setRotationServo(CRServo rotationServo) {
        this.rotationServo = rotationServo;
    }
}