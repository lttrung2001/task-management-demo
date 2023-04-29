package com.lttrung.taskmanagement.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;
import org.springframework.context.annotation.Bean;

public class BeanHelper {
    private static final String DEFAULT_LOGGER = "com.lttrung.taskmanagement.DEFAULT_LOGGER";

    @Bean
    public Logger providesLogger() {
        Logger logger = LoggerFactory.getLogger(DEFAULT_LOGGER);
        logger.isEnabledForLevel(Level.DEBUG);
        return logger;
    }
}
