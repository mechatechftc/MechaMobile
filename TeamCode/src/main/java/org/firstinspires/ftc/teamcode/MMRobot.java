package org.firstinspires.ftc.teamcode;

import com.edinaftc.ninevolt.Ninevolt;
import com.edinaftc.ninevolt.core.hw.drivetrain.StandardMovement;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.edinaftc.ninevolt.core.hw.Hardware;
import com.edinaftc.ninevolt.core.hw.Hardware.MotorMode;
import com.edinaftc.ninevolt.core.hw.HardwareBuilder;
import com.edinaftc.ninevolt.core.hw.drivetrain.Movement;



public class MMRobot {
    private static final int    PULSES_PER_MOTOR_REV  = 28;
    private static final double DRIVE_GEAR_REDUCTION  = 40.0;
    private static final double WHEEL_DIAMETER_INCHES = 4;
    private static final double PULSES_PER_INCH = (PULSES_PER_MOTOR_REV *
            DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);

    private Hardware hardware;
    private Movement movement;
    private OpMode ctx;
    private LinearOpMode ctxl;
    private MotorMode motorMode;

    public static BNO055IMU.Parameters getIMUParameters() {
        // Create Bosch IMU parameters
        BNO055IMU.Parameters imuParameters = new BNO055IMU.Parameters();
        imuParameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        imuParameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        imuParameters.loggingEnabled = true;
        imuParameters.loggingTag = "IMU";
        imuParameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
        imuParameters.mode = BNO055IMU.SensorMode.IMU;
        return imuParameters;
    }

    public MMRobot(OpMode _ctx) throws Exception {
        motorMode = MotorMode.FOUR_MOTORS;
        this.ctx = _ctx;
        Ninevolt.setConfig(MMConfig.getInstance());
        hardware = initializeHardware(ctx.hardwareMap);
        movement = new StandardMovement(hardware, ctx, motorMode);

    }

    public MMRobot(LinearOpMode _ctxl) throws Exception {
        this.ctxl = _ctxl;
        Ninevolt.setConfig(MMConfig.getInstance());
        hardware = initializeHardware(ctxl.hardwareMap);
        movement = new StandardMovement(hardware, ctxl, motorMode);


    }

    private Hardware initializeHardware(HardwareMap hardwareMap) throws Exception {
        HardwareBuilder hb = new HardwareBuilder(hardwareMap);
        hb.setMotorDirection(DcMotor.Direction.FORWARD);
        hb
                .addMotorFL("motorFL")
                .addMotorFR("motorFR")
                .addMotorBL("motorBL")
                .addMotorBR("motorBR")
                .addBoschIMU("imu", MMRobot.getIMUParameters());
        hardware = hb.build();
        hb = null;
        hardware.init();

         = hardwareMap.get(DcMotor.class, "armRaise");
        armExtendMotor = hardwareMap.get(DcMotor.class, "armExtend");

        armRaiseMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        armExtendMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        return hardware;
    }

    public void armRaise(float powerLevel) {
        armRaiseMotor.setPower(powerLevel);
    }


    public void armExtend(float powerLevel) {
        armExtendMotor.setPower(powerLevel);
    }


    public Movement getMovement() {
        return movement;
    }

    public Hardware getHardware() {
        return hardware;
    }
}