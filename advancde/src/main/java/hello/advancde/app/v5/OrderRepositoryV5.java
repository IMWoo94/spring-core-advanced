package hello.advancde.app.v5;

import org.springframework.stereotype.Repository;

import hello.advancde.trace.callback.TraceCallback;
import hello.advancde.trace.callback.TraceTemplate;
import hello.advancde.trace.logtrace.LogTrace;

@Repository
public class OrderRepositoryV5 {

	private final TraceTemplate traceTemplate;

	public OrderRepositoryV5(LogTrace trace) {
		this.traceTemplate = new TraceTemplate(trace);
	}

	public void save(String itemId) {
		traceTemplate.execute("OrderRepository.save()", (TraceCallback<Void>)() -> {
			// 저장 로직
			if (itemId.equals("ex")) {
				throw new IllegalStateException("예외 발생");
			}
			sleep(1000);
			return null;
		});
	}

	private void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
