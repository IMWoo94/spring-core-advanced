package hello.aop.pointcut;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import hello.aop.member.MemberCheck;
import hello.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@Import(AtAnnotationTest.AtAnnotationAspect.class)
public class AtAnnotationTest {

	@Autowired
	MemberServiceImpl memberService;

	@Autowired
	MemberCheck memberCheck;

	@Test
	void success() {
		log.info("memberService Proxy={}", memberService.getClass());
		memberService.hello("helloA");
		memberService.internal("test");
		memberService.atArgs(memberCheck);
	}

	@Slf4j
	@Aspect
	static class AtAnnotationAspect {
		@Around("@annotation(hello.aop.member.annotation.MethodAop)")
		public Object doAtAnnotation(ProceedingJoinPoint joinPoint) throws Throwable {
			log.info("[@annotation] {}", joinPoint.getSignature());
			return joinPoint.proceed();
		}

		@Around("execution(* hello.aop.member.*.*(..))&& @args(hello.aop.member.annotation.CheckAop)")
		public Object doAtArgs(ProceedingJoinPoint joinPoint) throws Throwable {
			log.info("[@args] {}", joinPoint.getSignature());
			return joinPoint.proceed();
		}
	}
}
