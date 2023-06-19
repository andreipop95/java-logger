package com.basic.logger.basiclogger;

import com.basic.logger.writers.LogTarget;
import com.basic.logger.writers.LogWriter;

public interface Logger {

    void debug(String message);

    void info(String message);

    void warn(String message);

    void error(String message);

    void addLogWriter(LogTarget name, LogWriter logWriter);

    boolean removeLogWriter(LogTarget name);

    void updateLogWriterSeverity(LogTarget logTarget, LogLevel logLevel);
}
