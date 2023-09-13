package hello.aop.internalcall;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
 애초에 내부 메서드를 호출하지않도록 내부 구조를 변경
 spring에서 권고하는 방식이기도 합니다.
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class CallServiceV3 {

	private final InternalService internalService;

	public void external() {
		log.info("call external");
		internalService.interanl();
	}
}
