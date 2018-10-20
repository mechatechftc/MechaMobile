package org.firstinspires.ftc.teamcode.OpModes;

import com.edinaftc.ninevolt.core.hw.drivetrain.Movement;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.Hardware;
import org.firstinspires.ftc.teamcode.Functions.MMArm;
import org.firstinspires.ftc.teamcode.Functions.MMHook;
import org.firstinspires.ftc.teamcode.MMRobot2;

@Autonomous(name="Pushbot: Auto Drive By Encoder", group="Pushbot")
@Disabled
/*
 * Created by Nickolas Idrogo-Lam and Eric Seng on 10/16/18
 */
public class Auto1 extends LinearOpMode {

    private MMRobot2 robot;
    private MMArm arm;
    private MMHook hook;
    private Movement movement;
    private Hardware hardware;
    private int block;
    private double lastBlockUpdateTime;

    @Override
    public void runOpMode() throws InterruptedException {

    }
}
