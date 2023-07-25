package com.example.hellospring;

import com.example.hellospring.repository.*;
import com.example.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

//    @Autowired
//    DataSource dataSource; // spring에서 지원해줌

    EntityManager entityManager;

    @Autowired
    public SpringConfig(EntityManager entityManager) {
        this.entityManager = entityManager ;
    }

    @Bean
    public MemberService memberservice() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(entityManager);
    }


}