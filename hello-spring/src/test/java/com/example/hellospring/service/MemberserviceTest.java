package com.example.hellospring.service;

import com.example.hellospring.domain.Member;
import com.example.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberserviceTest {

    MemberService memberservice;
    MemoryMemberRepository memberRepository;

    /*
      테스트에서 만든게 새로운 인스턴스이기 때문에 같은 레포지토리로 테스트 하는게 맞음...
      이렇게 했었을 때는 memberservice 안에서 다시 new memmoryMemberRepository 해서 사용했었
      그래서 이렇게 하면 member 안에 있는 레포지토리와 테스트케이스에서 사용하는 레포지토리 인스턴스가 다른 문제가 있었음
      Memberservice memberservice = new Memberservice();
      MemoryMemberRepository memberRepository = new MemoryMemberRepository();
     */
    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberservice = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }


    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("hello");

        // when
        Long saveId = memberservice.join(member);

        // then
        Member member1 = memberservice.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(member1.getName());
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberservice.join(member1);
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> memberservice.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
/*
        try {
            memberservice.join(member2);
            fail();
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
        }
*/
        // then
    }

    @Test
    void 회원전체조회() {
    }

    @Test
    void 회원조회() {
    }
}