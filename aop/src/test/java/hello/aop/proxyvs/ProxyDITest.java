package hello.aop.proxyvs;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.objenesis.Objenesis;
import org.springframework.objenesis.ObjenesisStd;
import org.springframework.objenesis.instantiator.ObjectInstantiator;

import hello.aop.member.MemberService;
import hello.aop.member.MemberServiceImpl;
import hello.aop.proxyvs.code.ProxyDIAspect;
import lombok.extern.slf4j.Slf4j;

@Slf4j
// @SpringBootTest(properties = {"spring.aop.proxy-target-class=false"}) // JDK dynamic proxy
@SpringBootTest(properties = {"spring.aop.proxy-target-class=true"}) // GCLIB
@Import(ProxyDIAspect.class)
public class ProxyDITest {

	@Autowired
	MemberService memberService;

	@Autowired
	MemberServiceImpl memberServiceImpl;

	@Test
	void go() {
		log.info("memberService class={}", memberService.getClass());
		log.info("memberServiceImpl class={}", memberServiceImpl.getClass());
		memberService.hello("hello");

		// spring aop 에서 CGLIB 의 단점에서 기본생성자 필수, 생성자 호출 2번 호출 되는 문제가 있다.
		// 이 문제를 해결하는 방식으로 Objenesis를 통해서 해결.
		// Objenesis 는 기본적으로 생성자 호출 방식의 객체 생성 방식이 아닌 생성자 없이 객체를 생성하는 방식이다.
		Objenesis o = new ObjenesisStd();
		Objenesis objenesis = new ObjenesisStd(); // 또는 ObjenesisSerializer
		ObjectInstantiator instantiator = objenesis.getInstantiatorOf(MemberServiceImpl.class);

		MemberServiceImpl test1 = (MemberServiceImpl)instantiator.newInstance();
		MemberServiceImpl test2 = (MemberServiceImpl)instantiator.newInstance();

	}
}
