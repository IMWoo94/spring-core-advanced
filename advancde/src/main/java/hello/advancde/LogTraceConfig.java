package hello.advancde;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.advancde.trace.logtrace.LogTrace;
import hello.advancde.trace.logtrace.ThreadLocalLogTrace;

@Configuration
public class LogTraceConfig {

	@Bean
	public LogTrace logTrace() {
		// return new FieldLogTrace();
		return new ThreadLocalLogTrace();
	}
}
