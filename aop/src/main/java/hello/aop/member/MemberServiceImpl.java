package hello.aop.member;

import org.springframework.stereotype.Component;

import hello.aop.member.annotation.ClassAop;
import hello.aop.member.annotation.MethodAop;

@ClassAop
@Component
public class MemberServiceImpl implements MemberService {

	public MemberServiceImpl() {
		System.out.println("member service impl constructor");
	}

	@Override
	@MethodAop("test value")
	public String hello(String param) {
		return "ok";
	}

	public String internal(String param) {
		return "ok";
	}

	public String internalParam(String a, String b) {
		return "ok";
	}

	public String internalMethod(int a, String b, int c) {
		return "ok";
	}

	public String atArgs(MemberCheck memberCheck) {
		return "ok";
	}

}
