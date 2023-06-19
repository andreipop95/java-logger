package com.app.demoapp;

import com.basic.logger.basiclogger.BasicLoggerFactory;
import com.basic.logger.basiclogger.LogLevel;
import com.basic.logger.basiclogger.Logger;
import com.basic.logger.writers.EmailLogWriter;
import com.basic.logger.writers.FileLogWriter;
import com.basic.logger.writers.LogTarget;
import com.basic.logger.writers.ServerApiLogWriter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoAppApplication.class, args);

		Logger logger = BasicLoggerFactory.getLogger();
		logger.info("This is a test logging message");

		logger.addLogWriter(LogTarget.FILE, new FileLogWriter("testFile.txt"));
		logger.addLogWriter(LogTarget.EMAIL, new EmailLogWriter());
		logger.addLogWriter(LogTarget.SERVER_API, new ServerApiLogWriter());

		System.out.println("-------------------------------------------------");
		printTestLogMessage(logger);

		logger.updateLogWriterSeverity(LogTarget.EMAIL, LogLevel.INFO);

		System.out.println("-------------------------------------------------");
		printTestLogMessage(logger);

		logger.updateLogWriterSeverity(LogTarget.FILE, LogLevel.DEBUG);
		System.out.println("-------------------------------------------------");
		printTestLogMessage(logger);

		logger.removeLogWriter(LogTarget.SERVER_API);
		System.out.println("-------------------------------------------------");
		printTestLogMessage(logger);

		logger.removeLogWriter(LogTarget.FILE);
		System.out.println("-------------------------------------------------");
		printTestLogMessage(logger);

		logger.updateLogWriterSeverity(LogTarget.CONSOLE, LogLevel.DEBUG);
		logger.updateLogWriterSeverity(LogTarget.EMAIL, LogLevel.ERROR);
		System.out.println("-------------------------------------------------");
		printTestLogMessage(logger);
	}

	public static void printTestLogMessage(Logger logger) {
		logger.info("This is an info log message");
		logger.debug("This is a debug message");
		logger.warn("This is a warn message");
		logger.error("This an error message");
	}

}
