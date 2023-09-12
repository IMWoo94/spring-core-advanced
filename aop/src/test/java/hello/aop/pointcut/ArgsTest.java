package hello.aop.pointcut;

import static org.assertj.core.api.Assertions.*;

import java.lang.reflect.Method;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import hello.aop.member.MemberServiceImpl;

public class ArgsTest {

	/*
	execution의 args 부분과 동일하다.
	다만 execution은 args의 타입이 정확하게 맞아야하고
	args는 부모 타입 까지 허용
	 */

	AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();

	Method helloMethod;

	@BeforeEach
	public void init() throws NoSuchMethodException {
		helloMethod = MemberServiceImpl.class.getMethod("hello", String.class);
	}

	private AspectJExpressionPointcut pointcut(String expression) {
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression(expression);
		return pointcut;
	}

	@Test
	void args() {
		//hello(String)과 매칭
		assertThat(pointcut("args(String)").matches(helloMethod, MemberServiceImpl.class)).isTrue();
		assertThat(pointcut("args(Object)").matches(helloMethod, MemberServiceImpl.class)).isTrue();
		assertThat(pointcut("args()").matches(helloMethod, MemberServiceImpl.class)).isFalse();
		assertThat(pointcut("args(..)").matches(helloMethod, MemberServiceImpl.class)).isTrue();
		assertThat(pointcut("args(*)").matches(helloMethod, MemberServiceImpl.class)).isTrue();
		assertThat(pointcut("args(String,..)").matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}

	/**
	 * execution(* *(java.io.Serializable)): 메서드의 시그니처로 판단 (정적)
	 * args(java.io.Serializable): 런타임에 전달된 인수로 판단 (동적)
	 */
	@Test
	void argsVsExecution() {
		//Args
		assertThat(pointcut("args(String)").matches(helloMethod, MemberServiceImpl.class)).isTrue();
		assertThat(pointcut("args(java.io.Serializable)").matches(helloMethod, MemberServiceImpl.class)).isTrue();
		assertThat(pointcut("args(Object)").matches(helloMethod, MemberServiceImpl.class)).isTrue();
		//Execution
		assertThat(pointcut("execution(* *(String))").matches(helloMethod, MemberServiceImpl.class)).isTrue();
		assertThat(
			pointcut("execution(* *(java.io.Serializable))").matches(helloMethod, MemberServiceImpl.class)).isFalse();
		assertThat(pointcut("execution(* *(Object))").matches(helloMethod, MemberServiceImpl.class)).isFalse();
	}
}
