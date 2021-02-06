package com.example.Helloworldself.service;

import com.example.Helloworldself.domain.Member;
import com.example.Helloworldself.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {

    MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Member join(Member member) {
        memberRepository.save(member);
        return member;
    }

    /**
     * 이름으로 맴버 리턴
     */
    public Optional<Member> findOne(String name) {
        return memberRepository.findByName(name);
    }

    /**
     * 아이디로 맴버 리턴
     */
    public Optional<Member> findOne(long id) {
        return memberRepository.findById(id);
    }

}
