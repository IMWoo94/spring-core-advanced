package hello.advancde.app.v1;

import org.springframework.stereotype.Service;

import hello.advancde.trace.TraceStatus;
import hello.advancde.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceV1 {

	private final OrderRepositoryV1 orderRepository;
	private final HelloTraceV1 trace;

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
