package com.example.hellospring.repository;

import com.example.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// 객체, pk 타입을 파라미터에 넣어줌
// 인터페이스는 다중 상속
// JPA 레포지토리를 상속받으면 자동으로 빈에 등록됨 그리고 spring-data-jpa가 알아서 찾아 씀
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository{

    // select m from Member m where m.name = ?로 번역됨
    @Override
    Optional<Member> findByName(String name);
}
