package com.example.My_Spring.repository;

import com.example.My_Spring.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
    final private MemoryMemberRepository repository = new MemoryMemberRepository();
    @BeforeEach
    void setUp() {
        repository.clearStore();
    }

    @Test
    void save() {
        Member spring = new Member();
        spring.setName("Happy");
        repository.save(spring);

        assertThat(repository.findByName("Happy").isPresent()).isEqualTo(true);
    }

    @Test
    void findById() {
        Member StephenPuth = new Member();
        StephenPuth.setName("Stephen Puth");
        repository.save(StephenPuth);
        assertThat(repository.findById(0).isPresent()).isEqualTo(true);


    }

    @Test
    void findByName() {
        Member spring = new Member();
        spring.setName("Happy");
        repository.save(spring);

        assertThat(repository.findByName("Happy").isPresent()).isEqualTo(true);
    }

    @Test
    void findAll() {

        Member spring1 = new Member();
        spring1.setName("Happy");
        repository.save(spring1);

        Member spring2 = new Member();
        spring2.setName("Happy2");
        repository.save(spring2);

        Member spring3 = new Member();
        spring3.setName("Happy3");
        repository.save(spring3);

        Member spring4 = new Member();
        spring4.setName("Happy4");
        repository.save(spring4);

        assertThat(repository.findAll().size()).isEqualTo(4);

    }
}