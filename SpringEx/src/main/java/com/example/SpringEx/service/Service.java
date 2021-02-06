package com.example.SpringEx.service;

import com.example.SpringEx.domain.Member;
import com.example.SpringEx.repository.MemberRepository;
import com.example.SpringEx.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class Service {

    private final MemberRepository repository = new MemoryMemberRepository();

    /**
     * 회원가입
     */
    public long join(Member member) {
        validateDuplicateMember(member);
        repository.save(member);

        return member.getId();
    }

    /**
     * Full member lookup
     */
    public List<Member> findMembers() {
        return repository.findALl();
    }

    /**
     * findById
     */
    public Optional<Member> findById(long memberId) {
        return repository.findById(memberId);
    }

    /**
     * 이미 가입된 회원이었는지 조회
     */
    private void validateDuplicateMember(Member member) {
        repository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원 입니다. ");
                });
    }


}
