package com.basic.logger.writers;

import com.basic.logger.basiclogger.LogLevel;

public class ConsoleLogWriter extends LogWriter {

    public ConsoleLogWriter() {
        super(LogLevel.INFO);
    }

    public ConsoleLogWriter(LogLevel minimumLogLevel) {
        super(minimumLogLevel);
    }

    @Override
    public void writeLog(LogLevel logLevel, String logMessage) {
        if (minimumLogLevel.ordinal() <= logLevel.ordinal()) {
            System.out.printf("[%-10s] %s%n", LogTarget.CONSOLE, logMessage);
        }
    }
}
