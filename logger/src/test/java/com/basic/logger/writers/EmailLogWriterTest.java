package com.basic.logger.writers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.basic.logger.BaseTest;
import com.basic.logger.basiclogger.LogLevel;
import org.junit.jupiter.api.Test;

public class EmailLogWriterTest extends BaseTest {

    @Test
    public void testWriteLog() {
        EmailLogWriter emailLogWriter = new EmailLogWriter();

        String message = "Email log test log message.";
        emailLogWriter.writeLog(LogLevel.ERROR, message);
        String actualOutput = outputStream.toString();

        assertEquals(getFormattedMessage(message, LogTarget.EMAIL), actualOutput);
    }
}
