package com.basic.logger.writers;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.basic.logger.BaseTest;
import com.basic.logger.basiclogger.LogLevel;
import org.junit.jupiter.api.Test;

public class ConsoleLogWriterTest extends BaseTest {

    @Test
    public void testWriteLog() {
        ConsoleLogWriter consoleLogWriter = new ConsoleLogWriter();

        String message = "Console log test message";
        consoleLogWriter.writeLog(LogLevel.INFO, message);
        String actualOutput = outputStream.toString();

        assertEquals(getFormattedMessage(message, LogTarget.CONSOLE), actualOutput);
    }

    @Test
    public void testWriteLogDifferentSeverityLevel() {
        ConsoleLogWriter consoleLogWriter = new ConsoleLogWriter();

        String message = "Console log test message";
        consoleLogWriter.setMinimumLogLevel(LogLevel.WARNING);
        consoleLogWriter.writeLog(LogLevel.DEBUG, message);
        assertEquals(outputStream.toString(), "");

        consoleLogWriter.writeLog(LogLevel.ERROR, message);
        assertEquals(outputStream.toString(), getFormattedMessage(message, LogTarget.CONSOLE));
    }
}
