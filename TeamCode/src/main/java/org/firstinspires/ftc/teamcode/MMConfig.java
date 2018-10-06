package org.firstinspires.ftc.teamcode;

import com.edinaftc.ninevolt.Config;

public class MMConfig extends Config{
    private static MMConfig INSTANCE = new MMConfig();
    private Config.LoggingLevel loggingLevel;

    public static MMConfig getInstance() {
        return INSTANCE;
    }
    private MMConfig() {
        setLoggingLevel(Config.LoggingLevel.VERBOSE);
    }

}
