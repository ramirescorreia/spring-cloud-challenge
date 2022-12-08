package br.com.caelum.eats.pagamento.config;

import java.nio.charset.Charset;

import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import biz.paluch.logging.gelf.logback.GelfLogbackAppender;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.filter.ThresholdFilter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.encoder.LayoutWrappingEncoder;

public class LoggingIntializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

	@Override
	public void initialize(ConfigurableApplicationContext applicationContext) {
		LoggerContext logContext = (LoggerContext) LoggerFactory.getILoggerFactory();
		
		PatternLayout pattern = new PatternLayout();
		pattern.setContext(logContext);
		pattern.setPattern("%clr(%d{yyyy-MM-dd HH:mm:ss.SSS}){faint} %clr(${LOG_LEVEL_PATTERN:-%5p}) %clr(${PID:- }){magenta} %clr(---){faint} %clr([%15.15t]){faint} %clr(%-40.40logger{39}){cyan} %clr(:){faint} %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}");
		pattern.start();
		
		LayoutWrappingEncoder<ILoggingEvent> encoder = new LayoutWrappingEncoder<ILoggingEvent>();
		encoder.setContext(logContext);
		encoder.setCharset(Charset.forName("utf-8"));
		encoder.setLayout(pattern);
		
		ConsoleAppender<ILoggingEvent> consoleAppender = new ConsoleAppender<>();
		consoleAppender.setContext(logContext);
		consoleAppender.setName("STDOUT");
		consoleAppender.setEncoder(encoder);
		consoleAppender.start();
		
		ThresholdFilter filter = new ThresholdFilter();
		filter.setLevel("INFO");
		
		GelfLogbackAppender gelfAppender = new GelfLogbackAppender();
		gelfAppender.setHost("udp:localhost");
		gelfAppender.setPort(12201);
		gelfAppender.setVersion("1.0");
		gelfAppender.setFacility("logstash-gelf");
		gelfAppender.setFilterStackTrace(true);
		gelfAppender.setIncludeLocation(true);
		gelfAppender.setTimestampPattern("yyyy-MM-dd HH:mm:ss,SSS");
		gelfAppender.setMaximumMessageSize(4096);
		gelfAppender.setIncludeFullMdc(true);
		gelfAppender.addFilter(filter);
		gelfAppender.start();
		
		Logger log = logContext.getLogger(Logger.ROOT_LOGGER_NAME);
		log.setLevel(Level.INFO);
		log.addAppender(consoleAppender);
		log.addAppender(gelfAppender);
	}
}
