package com.basic.logger.writers;

import com.basic.logger.basiclogger.LogLevel;

public abstract class LogWriter {

    protected LogLevel minimumLogLevel;

    public LogWriter(LogLevel logLevel) {
        this.minimumLogLevel = logLevel;
    }

    public void setMinimumLogLevel(LogLevel logLevel) {
        this.minimumLogLevel = logLevel;
    }

    public abstract void writeLog(LogLevel logLevel, String logMessage);
}
