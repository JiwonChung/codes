package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;


class DBMemberRepositoryTest {


    @Test
    public void save() {
        Member member = new Member();
        member.setName("delete");

        DBMemberRepository repository = new DBMemberRepository();
        assertThat(repository.save(member)).isEqualTo(member);
    }

    @Test
    void findById() {
        DBMemberRepository repository = new DBMemberRepository();
        assertThat(repository.findById(1).get().getName()).isEqualTo("superjiwon");
    }

    @Test
    void findByName() {
        DBMemberRepository repository = new DBMemberRepository();
        assertThat(repository.findByName("superjiwon").get().getId()).isEqualTo(1L);
    }

    @Test
    void findAll() {
//        DBMemberRepository repository = new DBMemberRepository();
//        assertThat(repository.findAll().size()).isEqualTo(9);
    }
}