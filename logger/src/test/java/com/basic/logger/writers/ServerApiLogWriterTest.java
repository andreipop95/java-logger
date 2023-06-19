package com.basic.logger.writers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.basic.logger.BaseTest;
import com.basic.logger.basiclogger.LogLevel;
import org.junit.jupiter.api.Test;

public class ServerApiLogWriterTest extends BaseTest {

    @Test
    public void testWriteLog() {
        ServerApiLogWriter serverApiLogWriter = new ServerApiLogWriter();

        String message = "ServerApi log test log message.";
        serverApiLogWriter.writeLog(LogLevel.INFO, message);
        String actualOutput = outputStream.toString();

        assertEquals(getFormattedMessage(message, LogTarget.SERVER_API), actualOutput);
    }
}
