package hello.aop.proxyvs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

import hello.aop.member.MemberService;
import hello.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProxyCastingTest {

	@Test
	void jdkProxy() {
		MemberServiceImpl target = new MemberServiceImpl();

		ProxyFactory proxyFactory = new ProxyFactory(target);

		proxyFactory.setProxyTargetClass(false); // JDK 동적 프록시

		// 프록시를 인터페이스로 캐스팅 성공
		MemberService memberServiceProxy = (MemberService)proxyFactory.getProxy();
		
		// 인터페이스 구현 객체로 캐스팅 실패 java.lang.ClassCastException 예외 발생
		Assertions.assertThrows(ClassCastException.class, () -> {
			MemberServiceImpl castingMemberService = (MemberServiceImpl)memberServiceProxy;
		});

	}

	@Test
	void cglibProxy() {
		MemberServiceImpl target = new MemberServiceImpl();

		ProxyFactory proxyFactory = new ProxyFactory(target);

		proxyFactory.setProxyTargetClass(true); // CGLIB

		// 프록시를 인터페이스로 캐스팅 성공
		MemberService memberServiceProxy = (MemberService)proxyFactory.getProxy();

		log.info("proxy class={}", memberServiceProxy.getClass());

		// 인터페이스 구현 객체로 캐스팅 성공
		MemberServiceImpl castingMemberService = (MemberServiceImpl)memberServiceProxy;

	}
}
