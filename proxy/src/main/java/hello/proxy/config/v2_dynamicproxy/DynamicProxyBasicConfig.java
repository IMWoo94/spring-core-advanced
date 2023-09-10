package hello.proxy.config.v2_dynamicproxy;

import java.lang.reflect.Proxy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.proxy.app.v1.OrderControllerV1;
import hello.proxy.app.v1.OrderControllerV1Impl;
import hello.proxy.app.v1.OrderRepositoryV1;
import hello.proxy.app.v1.OrderRepositoryV1Impl;
import hello.proxy.app.v1.OrderServiceV1;
import hello.proxy.app.v1.OrderServiceV1Impl;
import hello.proxy.config.v2_dynamicproxy.handler.LogTraceBasicHandler;
import hello.proxy.trace.logtrace.LogTrace;

@Configuration
public class DynamicProxyBasicConfig {

	@Bean
	public OrderControllerV1 orderControllerV1(LogTrace logTrace) {
		OrderControllerV1 controllerV1 = new OrderControllerV1Impl(orderServiceV1(logTrace));

		Object proxy = Proxy.newProxyInstance(OrderControllerV1.class.getClassLoader(),
			new Class[] {OrderControllerV1.class},
			new LogTraceBasicHandler(controllerV1, logTrace));

		return (OrderControllerV1)proxy;
	}

	@Bean
	public OrderServiceV1 orderServiceV1(LogTrace logTrace) {
		OrderServiceV1 orderService = new OrderServiceV1Impl(orderRepositoryV1(logTrace));

		Object proxy = Proxy.newProxyInstance(OrderServiceV1.class.getClassLoader(),
			new Class[] {OrderServiceV1.class},
			new LogTraceBasicHandler(orderService, logTrace));

		return (OrderServiceV1)proxy;
	}

	@Bean
	public OrderRepositoryV1 orderRepositoryV1(LogTrace logTrace) {
		OrderRepositoryV1 orderRepository = new OrderRepositoryV1Impl();

		Object proxy = Proxy.newProxyInstance(OrderRepositoryV1.class.getClassLoader(),
			new Class[] {OrderRepositoryV1.class},
			new LogTraceBasicHandler(orderRepository, logTrace));

		return (OrderRepositoryV1)proxy;
	}
}
