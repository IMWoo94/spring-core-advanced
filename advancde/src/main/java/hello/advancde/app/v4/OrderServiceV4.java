package hello.advancde.app.v4;

import org.springframework.stereotype.Service;

import hello.advancde.trace.logtrace.LogTrace;
import hello.advancde.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceV4 {

	private final OrderRepositoryV4 orderRepository;
	private final LogTrace trace;

	// @RequiredArgsConstructor 을 사용하면 필수 생성자를 알아서 생성 해준다.
	// @Autowired // 생성자가 1개인 경우 자동으로 @Autowired 가 붙는다.
	// public OrderService(OrderRepositoryV0 orderRepository) {
	// 	this.orderRepository = orderRepository;
	// }

	public void orderItem(String itemId) {
		AbstractTemplate<Void> template = new AbstractTemplate<>(trace) {
			@Override
			protected Void call() {
				orderRepository.save(itemId);
				return null;
			}
		};
		template.execute("OrderService.orderItem()");

	}
}
