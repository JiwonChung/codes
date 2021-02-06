package com.example.Helloworldself.service;

import com.example.Helloworldself.domain.Member;
import com.example.Helloworldself.repository.MemberRepository;
import com.example.Helloworldself.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService = new MemberService(new MemoryMemberRepository());
    
    @Test
    void findOne() {
        Member member = new Member();
        member.setName("please Don't see");
        memberService.join(member);

        memberService.findOne("please Don't see").get();
    }
}