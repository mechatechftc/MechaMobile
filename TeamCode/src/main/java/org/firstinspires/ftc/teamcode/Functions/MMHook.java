package org.firstinspires.ftc.teamcode.Functions;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Eric S on 10/13/2018.
 */

public class MMHook {
    DcMotor hookMotor;
    private OpMode ctx;

    public MMHook(DcMotor hookMotor, OpMode ctx) {
        this.hookMotor = hookMotor;
        hookMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.ctx = ctx;
    }

    public void hookRaise(float powerLevel) {
        hookMotor.setPower(powerLevel);
        hookMotor.setDirection(DcMotor.Direction.FORWARD);
    }

    public void hookLower(float powerLevel) {
        hookMotor.setPower(powerLevel);
        hookMotor.setDirection(DcMotor.Direction.REVERSE);
    }
}
