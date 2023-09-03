package hello.advancde.app.v0;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceV0 {

	private final OrderRepositoryV0 orderRepository;

	// @RequiredArgsConstructor 을 사용하면 필수 생성자를 알아서 생성 해준다.
	// @Autowired // 생성자가 1개인 경우 자동으로 @Autowired 가 붙는다.
	// public OrderService(OrderRepositoryV0 orderRepository) {
	// 	this.orderRepository = orderRepository;
	// }

	public void orderItem(String itemId) {
		orderRepository.save(itemId);
	}
}
