package com.pixeldv.commons;

import org.jetbrains.annotations.Nullable;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LogHandler {

    private final Logger logger;

    public LogHandler(Logger logger) {
        this.logger = logger;
    }

    public void error(
            String message, @Nullable Throwable throwable,
            String... arguments
    ) {
        logger.log(Level.WARNING, message, arguments);

        if (throwable != null) {
            // print it using system err
            throwable.printStackTrace(System.err);
        }
    }

    public void error(String message, String... arguments) {
        error(message, null, arguments);
    }

    public void info(String message, String... arguments) {
        logger.log(Level.INFO, message, arguments);
    }

    public void severe(String message, String... arguments) {
        logger.log(Level.SEVERE, message, arguments);
    }
}
