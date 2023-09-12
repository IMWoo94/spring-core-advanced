package hello.aop.member;

import org.springframework.stereotype.Component;

import hello.aop.member.annotation.CheckAop;

@Component
@CheckAop
public class MemberCheck {
}
