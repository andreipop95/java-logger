package com.basic.logger.writers;

import com.basic.logger.basiclogger.LogLevel;

public class FileLogWriter extends LogWriter {

    public FileLogWriter(String filePath) {
        super(LogLevel.INFO);
    }

    public FileLogWriter(LogLevel logLevel) {
        super(logLevel);
    }

    @Override
    public void writeLog(LogLevel logLevel, String logMessage) {
        if (minimumLogLevel.ordinal() <= logLevel.ordinal()) {
            System.out.printf("[%-10s] %s%n", LogTarget.FILE, logMessage);
        }
    }
}
