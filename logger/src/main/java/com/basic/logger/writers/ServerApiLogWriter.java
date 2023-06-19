package com.basic.logger.writers;

import com.basic.logger.basiclogger.LogLevel;

public class ServerApiLogWriter extends LogWriter {

    public ServerApiLogWriter() {
        super(LogLevel.INFO);
    }

    public ServerApiLogWriter(LogLevel logLevel) {
        super(logLevel);
    }

    @Override
    public void writeLog(LogLevel logLevel, String logMessage) {
        if (minimumLogLevel.ordinal() <= logLevel.ordinal()) {
            System.out.printf("[%-10s] %s%n", LogTarget.SERVER_API, logMessage);
        }
    }
}
