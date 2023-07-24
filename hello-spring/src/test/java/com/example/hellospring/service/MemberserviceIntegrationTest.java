package com.example.hellospring.service;

import com.example.hellospring.domain.Member;
import com.example.hellospring.repository.JdbcMemberRepository;
import com.example.hellospring.repository.MemberRepository;
import com.example.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
/*
db에 commit을 해야 진짜로 db에 들어가는데 그 전에 롤백하면 다 돌아감
이 에노테이션을 테스트 케이스에 달면 다 하고 난 뒤에 롤백해줌
 */
class MemberserviceIntegrationTest {

    @Autowired
    MemberService memberservice;
    @Autowired
    MemberRepository memberRepository;

    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("hello11");

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


}