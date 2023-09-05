package hello.advancde.app.v5;

import org.springframework.stereotype.Service;

import hello.advancde.trace.callback.TraceCallback;
import hello.advancde.trace.callback.TraceTemplate;
import hello.advancde.trace.logtrace.LogTrace;

@Service
public class OrderServiceV5 {

	private final OrderRepositoryV5 orderRepository;
	private final TraceTemplate traceTemplate;

	public OrderServiceV5(OrderRepositoryV5 orderRepository, LogTrace trace) {
		this.orderRepository = orderRepository;
		this.traceTemplate = new TraceTemplate(trace);
	}

	// @RequiredArgsConstructor 을 사용하면 필수 생성자를 알아서 생성 해준다.
	// @Autowired // 생성자가 1개인 경우 자동으로 @Autowired 가 붙는다.
	// public OrderService(OrderRepositoryV0 orderRepository) {
	// 	this.orderRepository = orderRepository;
	// }

	public void orderItem(String itemId) {
		traceTemplate.execute("OrderService.orderItem()", (TraceCallback<Void>)() -> {
			orderRepository.save(itemId);
			return null;
		});

	}
}
