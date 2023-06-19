package com.basic.logger.writers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.basic.logger.BaseTest;
import com.basic.logger.basiclogger.LogLevel;
import org.junit.jupiter.api.Test;

public class FileLogWriterTest extends BaseTest {

    @Test
    public void testWriteLog() {
        FileLogWriter fileLogWriter = new FileLogWriter("TestFile.txt");

        String message = "File log test log message.";
        fileLogWriter.writeLog(LogLevel.INFO, message);
        String actualOutput = outputStream.toString();

        assertEquals(getFormattedMessage(message, LogTarget.FILE), actualOutput);
    }
}
