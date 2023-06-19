package com.basic.logger.basiclogger;

import com.basic.logger.writers.ConsoleLogWriter;
import com.basic.logger.writers.LogTarget;
import java.util.LinkedHashMap;
import java.util.Map;

public class BasicLoggerFactory {

    public static Logger getLogger() {
        ConsoleLogWriter consoleLogWriter = new ConsoleLogWriter();
        return new BasicLogger(new LinkedHashMap<>(Map.of(LogTarget.CONSOLE, consoleLogWriter)));
    }

}
