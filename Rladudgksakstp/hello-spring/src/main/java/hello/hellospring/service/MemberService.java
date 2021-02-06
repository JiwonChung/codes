package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


public class MemberService {
    private final MemberRepository repository;


    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    /**
     * 회원가입
     */
    public long join(Member member) {
        repository.findByName(member.getName())
                .ifPresent(
                        m -> {
                            throw new IllegalStateException("The user name already exist. ");
                        }
                );

        repository.save(member);
        return member.getId();
    }

    /**
     * 조회 by id
     */
    public Member findById(long id) {
        if (repository.findById(id).isPresent()) {
            return repository.findById(id).get();
        } else {
            throw new IllegalStateException("There is no user where id equals to " + id);
        }
    }

    /**
     * 조회 by name
     */
    public Member findByName(String name) {
        if (repository.findByName(name).isPresent()) {
            return repository.findByName(name).get();
        } else {
            throw new IllegalStateException("There is no user where name equals to " + name);
        }
    }

    /**
     * 전체조회

     */
    public List<Member> findMembers() {
        return repository.findAll();
    }





}
