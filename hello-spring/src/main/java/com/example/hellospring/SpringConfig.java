package com.example.hellospring;

import com.example.hellospring.aop.TimeTraceAop;
import com.example.hellospring.repository.*;
import com.example.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

//    @Autowired
//    DataSource dataSource; // spring에서 지원해줌

//    EntityManager entityManager;
//
//    @Autowired
//    public SpringConfig(EntityManager entityManager) {
//        this.entityManager = entityManager ;
//    }

    // 스프링 데이터가 만들어놓은 구현체 레포지토리가 등록됨
    private final MemberRepository memberRepository;

    // 스프링 컨테이너에서 멤버 레포지토리를 찾음 근데 등록해놓은게 없음
    // 근데 jparepository 상속받은 인테페이스가 있어서 그게 자동으로 빈에 등록됨 그래서 그거 찾아씀
    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Bean
    public MemberService memberservice() {
        return new MemberService(memberRepository);
    }

//    @Bean
//    public MemberRepository memberRepository() {
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(entityManager);
//    }

}