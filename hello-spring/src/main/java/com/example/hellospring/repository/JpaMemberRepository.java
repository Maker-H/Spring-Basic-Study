package com.example.hellospring.repository;

import com.example.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements  MemberRepository{

    // 라이브러리 받으면 스프링 부트가 자동으로 엔티티 메니저를 만들어줌
    // 데이터 통신을 들고 있어서 db랑 통신함
    private final EntityManager entityManager;

    public JpaMemberRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public Member save(Member member) {
        entityManager.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = entityManager.find(Member.class, id); // 조회할 타입이랑 식별자 넣어주기
        return Optional.ofNullable(member);
        //  null input이면 empty 반환
    }

    // 단건은 그냥 해도 되는데 여러 가지 출력은 쿼리 써야 함
    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = entityManager.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        // 객체 대상으로 쿼리 날리면 조회 됨
        // select 하면 객체 자체를 select 함
        return entityManager.createQuery("select m from Member m", Member.class).getResultList();
    }
}
