package com.example.SpringEx.repository;


import com.example.SpringEx.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    void save() {


        Member member = new Member();
        member.setName("JiwonChung");
        repository.save(member);
        Assertions.assertEquals(member, repository.findById(member.getId()).get());
    }

    @Test
    void findById() {
        Member member = new Member();
        member.setName("Spring");
        repository.save(member);
        org.assertj.core.api.Assertions.assertThat(member).isEqualTo(repository.findById(member.getId()).get());
    }

    @Test
    void findByName() {
//        repository.clearStore();
        Member member1 = new Member();
        member1.setName("Jiwon1");
        repository.save(member1);

        org.assertj.core.api.Assertions.assertThat(member1).isEqualTo(repository.findByName(member1.getName()).get());
//        System.out.println(repository.findByName("Jiwon1").get());

    }

    @Test
    void findAll() {

        Member m1 = new Member();
        m1.setName("Jiwon1");
        repository.save(m1);

        Member m2 = new Member();
        m2.setName("Jiwon2");
        repository.save(m2);

        Member m3 = new Member();
        m3.setName("Jiwon3");
        repository.save(m3);

        Member m4 = new Member();
        m4.setName("Jiwon4");
        repository.save(m4);

        org.assertj.core.api.Assertions.assertThat(repository.findALl().size()).isEqualTo(4);
        
    }


}
