package com.basic.logger.basiclogger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.basic.logger.BaseTest;
import com.basic.logger.writers.EmailLogWriter;
import com.basic.logger.writers.FileLogWriter;
import com.basic.logger.writers.LogTarget;
import com.basic.logger.writers.ServerApiLogWriter;
import org.junit.jupiter.api.Test;

public class BasicLoggerTest extends BaseTest {

    private final Logger basicLogger = BasicLoggerFactory.getLogger();
    private final String message = "Logger test message";

    @Test
    public void testConsoleOutputForVariousSeverityLevels() {
        basicLogger.info(message);
        basicLogger.updateLogWriterSeverity(LogTarget.CONSOLE, LogLevel.INFO);

        String output = outputStream.toString();
        assertTrue(output.matches(getRegexToMatch(LogTarget.CONSOLE, LogLevel.INFO, message)));

        outputStream.reset();
        basicLogger.updateLogWriterSeverity(LogTarget.CONSOLE, LogLevel.ERROR);
        basicLogger.info(message);

        String newOutput = outputStream.toString();
        assertEquals(newOutput, "");
    }

    @Test
    public void addNewLogWriter() {
        basicLogger.addLogWriter(LogTarget.FILE, new FileLogWriter("testFile.txt"));

        basicLogger.info(message);
        String consoleRegex = getRegexToMatch(LogTarget.CONSOLE, LogLevel.INFO, message);
        String fileRegex = getRegexToMatch(LogTarget.FILE, LogLevel.INFO, message);
        var output = outputStream.toString();

        assertTrue(output.matches(consoleRegex + fileRegex));

        outputStream.reset();
        basicLogger.updateLogWriterSeverity(LogTarget.FILE, LogLevel.ERROR);
        basicLogger.info(message);

        assertTrue(outputStream.toString().matches(consoleRegex));

        outputStream.reset();
        basicLogger.updateLogWriterSeverity(LogTarget.FILE, LogLevel.DEBUG);
        basicLogger.error(message);

        consoleRegex = getRegexToMatch(LogTarget.CONSOLE, LogLevel.ERROR, message);
        fileRegex = getRegexToMatch(LogTarget.FILE, LogLevel.ERROR, message);

        assertTrue(outputStream.toString().matches(consoleRegex + fileRegex));

        basicLogger.removeLogWriter(LogTarget.FILE);
    }

    @Test
    public void removeLogWriter() {
        basicLogger.addLogWriter(LogTarget.FILE, new FileLogWriter(LogLevel.DEBUG));
        basicLogger.addLogWriter(LogTarget.EMAIL, new EmailLogWriter(LogLevel.DEBUG));
        basicLogger.addLogWriter(LogTarget.SERVER_API, new ServerApiLogWriter(LogLevel.DEBUG));
        basicLogger.updateLogWriterSeverity(LogTarget.CONSOLE, LogLevel.DEBUG);

        basicLogger.debug(message);
        String consoleRegex = getRegexToMatch(LogTarget.CONSOLE, LogLevel.DEBUG, message);
        String fileRegex = getRegexToMatch(LogTarget.FILE, LogLevel.DEBUG, message);
        String emailRegex = getRegexToMatch(LogTarget.EMAIL, LogLevel.DEBUG, message);
        String serverRegex = getRegexToMatch(LogTarget.SERVER_API, LogLevel.DEBUG, message);

        assertTrue(outputStream.toString().matches(consoleRegex + fileRegex + emailRegex + serverRegex));

        outputStream.reset();
        basicLogger.removeLogWriter(LogTarget.SERVER_API);
        basicLogger.updateLogWriterSeverity(LogTarget.CONSOLE, LogLevel.WARNING);
        basicLogger.updateLogWriterSeverity(LogTarget.EMAIL, LogLevel.WARNING);

        basicLogger.warn(message);
        consoleRegex = getRegexToMatch(LogTarget.CONSOLE, LogLevel.WARNING, message);
        fileRegex = getRegexToMatch(LogTarget.FILE, LogLevel.WARNING, message);
        emailRegex = getRegexToMatch(LogTarget.EMAIL, LogLevel.WARNING, message);

        assertTrue(outputStream.toString().matches(consoleRegex + fileRegex + emailRegex));
    }

    private String getRegexToMatch(LogTarget logTarget, LogLevel logLevel, String message) {
        return String.format(
                "\\[%s\\s*\\]\\s*\\d{4}-\\d{2}-\\d{2}\\s*\\d{2}:\\d{2}:\\d{2}\\s*\\[%s\\s*\\]\\s*%s\\n",
                logTarget.name(),
                logLevel.name(),
                message
        );
    }

}
