package com.hlnikniky.hle;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class Config {

    public static int elevatorRange = 64; // дальность поиска лифта, значение по умолчанию

    public static void synchronizeConfiguration(File configFile) {
        Configuration configuration = new Configuration(configFile);

        // Новая секция
        elevatorRange = configuration.getInt(
            "Elevator Range",
            "range",
            elevatorRange,
            4,
            256,
            "Maximum number of blocks the elevator will search up/down for another elevator block");

        if (configuration.hasChanged()) {
            configuration.save();
        }
    }
}
