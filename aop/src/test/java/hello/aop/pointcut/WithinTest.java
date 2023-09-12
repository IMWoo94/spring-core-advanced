package hello.aop.pointcut;

import static org.assertj.core.api.Assertions.*;

import java.lang.reflect.Method;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import hello.aop.member.MemberService;
import hello.aop.member.MemberServiceImpl;

public class WithinTest {
	/*
	execution 의 타입 부분만 사용한다고 생각
	 */

	AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
	Method helloMethod;

	@BeforeEach
	public void init() throws NoSuchMethodException {
		helloMethod = MemberServiceImpl.class.getMethod("hello", String.class);
	}

	@Test
	void withinExact() {
		pointcut.setExpression("within(hello.aop.member.MemberServiceImpl)");
		assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}

	@Test
	void withinStar() throws NoSuchMethodException {
		pointcut.setExpression("within(hello.aop.member.*Service*)");
		Method method = MemberServiceImpl.class.getMethod("internal", String.class);
		assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
		assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
	}

	@Test
	void withinSubPackage() throws NoSuchMethodException {
		pointcut.setExpression("within(hello.aop..*)");
		Method method = MemberService.class.getMethod("hello", String.class);
		assertThat(pointcut.matches(method, MemberService.class)).isTrue();
		assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}

	/*
	부모타입을 입력 하면 execution에서는 가능했지만 within에서는 정확하게 해당 타입으로 맞아야 한다.
	 */
	@Test
	void withinExactSuperType() {
		pointcut.setExpression("within(hello.aop.member.MemberService)");
		assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();
	}

	@Test
	@DisplayName("타켓의 타입에만 직접 적용, 인터페이스를 선정하면 안된다.")
	void withinSuperTypeFalse() {
		pointcut.setExpression("within(hello.aop.member.MemberService)");
		assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();
	}

	@Test
	@DisplayName("execution은 타입 기반, 인터페이스를 선정 가능.")
	void executionSuperTypeTrue() {
		pointcut.setExpression("execution(* hello.aop.member.MemberService.*(..))");
		assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}

}
