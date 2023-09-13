package hello.aop.internalcall;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CallServiceV0 {

	public void external() {
		log.info("call external");
		interanl(); // 내부 메서드 호출 ( this.internal())
	}

	public void interanl() {
		log.info("call internal");
	}
}
