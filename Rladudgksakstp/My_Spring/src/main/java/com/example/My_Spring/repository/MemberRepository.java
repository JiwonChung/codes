package com.example.My_Spring.repository;

import com.example.My_Spring.domain.Member;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository {
    Member save(Member member);

    Optional<Member> findById(long id);

    Optional<Member> findByName(String name);

    List<Member> findAll();
}
