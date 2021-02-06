package com.example.Helloworldself.repository;

import com.example.Helloworldself.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    @AfterEach
    void 클리어() {
        memberRepository.clearStore();
    }

    @Test
    void save() {
        Member member = new Member();
        member.setName("Aeroplane");

        memberRepository.save(member);

        Member member1 = memberRepository.findByName("Aeroplane").get();
        assertThat(member1).isEqualTo(member);
    }

    @Test
    void findById() {
        Member member = new Member();
        member.setName("jiwon0321");
        memberRepository.save(member);
        assertThat(member).isEqualTo(memberRepository.findById(1).get());
    }

    @Test
    void findAll() {
        Member member = new Member();
        member.setName("스프링 입니다. 1");
        memberRepository.save(member);

        Member member2 = new Member();
        member2.setName("스프링 입니다. 2");
        memberRepository.save(member2);

        Member member3 = new Member();
        member3.setName("스프링 입니다. 3");
        memberRepository.save(member3);

        assertThat(memberRepository.findAll().size()).isEqualTo(3);

    }
}