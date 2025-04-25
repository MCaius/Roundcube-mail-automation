package com.testing.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/*
* Centralized environment manager, responsible for determining and loading the environment config.
* Helps decouple environment configuration logic from test logic.
*/

public class EnvironmentManager {
    private static final Logger logger = LogManager.getLogger(EnvironmentManager.class);
    private static String environment;

    static {
        environment = System.getProperty("env", "staging");
        logger.debug("🛠 Loading config for env: {}", environment);
        ConfigReader.loadConfig(environment);
    }

    public static String getEnv() {
        return environment;
    }
}
