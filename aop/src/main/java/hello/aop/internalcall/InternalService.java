package hello.aop.internalcall;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class InternalService {
	
	public void interanl() {
		log.info("call internal");
	}
}
