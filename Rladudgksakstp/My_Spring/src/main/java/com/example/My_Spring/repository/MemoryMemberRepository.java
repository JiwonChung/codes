package com.example.My_Spring.repository;

import com.example.My_Spring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MembersRepository {
    long sequence = 0;
    Map<Long, Member> store = new HashMap<>();

    @Override
    public Member save(Member member) {
        member.setId(sequence);
        store.put(sequence++, member);
        return member;
    }

    @Override
    public Optional<Member> findById(long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values()
                .stream()
                .filter(
                        member -> member.getName().equals(name)
                ).findFirst();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
