package hello.aop.order.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Slf4j
public class AspectV2 {

	//hello.aop.order 패키지와 하위 패키지
	@Pointcut("execution(* hello.aop.order..*(..))")
	private void allOrder() {
	} // pointcut signature

	// hello.aop.order 패키지와 하위 패키지
	@Around("allOrder()")
	public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
		//join point 시그니처
		log.info("[log] {}", joinPoint.getSignature());

		return joinPoint.proceed();
	}
}
