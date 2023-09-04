package hello.advancde.app.v3;

import org.springframework.stereotype.Service;

import hello.advancde.trace.TraceStatus;
import hello.advancde.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceV3 {

	private final OrderRepositoryV3 orderRepository;
	private final LogTrace trace;

	// @RequiredArgsConstructor 을 사용하면 필수 생성자를 알아서 생성 해준다.
	// @Autowired // 생성자가 1개인 경우 자동으로 @Autowired 가 붙는다.
	// public OrderService(OrderRepositoryV0 orderRepository) {
	// 	this.orderRepository = orderRepository;
	// }

	public void orderItem(String itemId) {
		TraceStatus status = null;
		try {
			status = trace.begin("OrderService.orderItem()");
			orderRepository.save(itemId);
			trace.end(status);
		} catch (Exception e) {
			trace.exception(status, e);
			throw e;
		}

	}
}
