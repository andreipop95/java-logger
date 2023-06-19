package com.basic.logger;

import com.basic.logger.writers.LogTarget;
import com.basic.logger.writers.LogWriter;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {
    final PrintStream originalSystemOut = System.out;
    final public ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    final PrintStream customPrintStream = new PrintStream(outputStream);

    @BeforeEach
    public void setUp() {
        System.setOut(customPrintStream);
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalSystemOut);
    }

    protected String getFormattedMessage(String message, LogTarget logTarget) {
        return String.format("[%-10s] %s%n", logTarget.name(), message);
    }

}
