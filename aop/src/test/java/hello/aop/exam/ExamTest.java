package hello.aop.exam;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import hello.aop.exam.annotation.aop.RetryAspect;
import hello.aop.exam.annotation.aop.TraceAspect;
import lombok.extern.slf4j.Slf4j;

@Import({TraceAspect.class, RetryAspect.class})
@SpringBootTest
@Slf4j
class ExamTest {

	@Autowired
	ExamService examService;

	@Test
	void test() {
		for (int i = 0; i < 7; i++) {
			log.info("client i = {}", i);
			examService.request("data" + i);
		}
	}
}