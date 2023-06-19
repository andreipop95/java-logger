package com.basic.logger.basiclogger;

import com.basic.logger.writers.LogTarget;
import com.basic.logger.writers.LogWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


public class BasicLogger implements Logger {

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String LOGS_FORMAT = "%-20s [%-8s] %s";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

    private final Map<LogTarget, LogWriter> logWriters;

    public BasicLogger(Map<LogTarget, LogWriter> logWriters) {
        this.logWriters = logWriters;
    }

    public void addLogWriter(LogTarget logTarget, LogWriter logWriter) {
        if (!logWriters.containsKey(logTarget)) {
            logWriters.put(logTarget, logWriter);
        }
    }

    public boolean removeLogWriter(LogTarget logTarget) {
        if (logWriters.containsKey(logTarget)) {
            logWriters.remove(logTarget);
            return true;
        }

        return false;
    }

    @Override
    public void updateLogWriterSeverity(LogTarget logTarget, LogLevel logLevel) {
        if (logWriters.containsKey(logTarget)) {
            logWriters.get(logTarget).setMinimumLogLevel(logLevel);
        }
    }

    public void debug(String message) {
        log(LogLevel.DEBUG, message);
    }

    public void info(String message) {
        log(LogLevel.INFO, message);
    }

    public void warn(String message) {
        log(LogLevel.WARNING, message);
    }

    public void error(String message) {
        log(LogLevel.ERROR, message);
    }

    private void log(LogLevel level, String message) {

        String timestamp = dateFormat.format(new Date());
        String formattedMessage = String.format(LOGS_FORMAT, timestamp, level, message);

        logWriters.forEach((name, writer) -> {
            writer.writeLog(level, formattedMessage);
        });
    }
}
