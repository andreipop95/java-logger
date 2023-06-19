package com.basic.logger.writers;

import com.basic.logger.basiclogger.LogLevel;

public class EmailLogWriter extends LogWriter {

    public EmailLogWriter() {
        super(LogLevel.ERROR);
    }

    public EmailLogWriter(LogLevel logLevel) {
        super(logLevel);
    }

    @Override
    public void writeLog(LogLevel logLevel, String logMessage) {
        if (minimumLogLevel.ordinal() <= logLevel.ordinal()) {
            System.out.printf("[%-10s] %s%n", LogTarget.EMAIL, logMessage);
        }
    }
}
