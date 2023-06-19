package com.basic.logger.demo;

import com.basic.logger.basiclogger.BasicLoggerFactory;
import com.basic.logger.basiclogger.LogLevel;
import com.basic.logger.basiclogger.Logger;
import com.basic.logger.writers.FileLogWriter;
import com.basic.logger.writers.LogTarget;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoggerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoggerApplication.class, args);

		var log = LoggerFactory.getLogger(LoggerApplication.class);
		log.info("This is an original log");

		var logger = BasicLoggerFactory.getLogger();
		writeLogs(logger);

		logger.addLogWriter(LogTarget.FILE, new FileLogWriter("testLog"));
		writeLogs(logger);

		logger.updateLogWriterSeverity(LogTarget.FILE, LogLevel.WARNING);
		writeLogs(logger);
	}

	public static void writeLogs(Logger logger) {
		logger.debug("Debug message");
		logger.info("Info message");
		logger.warn("Warning message");
		logger.error("Error message");
	}

}
