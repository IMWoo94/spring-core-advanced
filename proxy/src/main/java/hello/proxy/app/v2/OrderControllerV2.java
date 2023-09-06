package hello.proxy.app.v2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping // controller 로 서비스 빈을 수동으로 등록하기 위해서
@ResponseBody
public class OrderControllerV2 {

	private final OrderServiceV2 orderService;

	public OrderControllerV2(OrderServiceV2 orderService) {
		this.orderService = orderService;
	}

	@GetMapping("/v2/request")
	public String request(String itemId) {
		orderService.orderItem(itemId);
		return "ok";
	}

	@GetMapping("/v2/no-log")
	public String noLog() {
		return "ok";
	}
}
