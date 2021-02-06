package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    void afterEach() {
        repository.clearStore();
    }

    @Test
    void save() {
        Member member = new Member();
        member.setName("Jiwon");
        repository.save(member);

        assertThat(member).isEqualTo(repository.findById(1).get());
    }

    @Test
    void findByName() {
        Member member = new Member();
        member.setName("Spring1");
        repository.save(member);

        Member member1 = new Member();
        member1.setName("Spring2");
        repository.save(member1);

        Member result = repository.findByName("Spring1").get();
        assertThat(result).isEqualTo(member);
    }

    @Test
    void findAll() {
        Member member = new Member();
        member.setName("Spring1");
        repository.save(member);

        Member member2 = new Member();
        member2.setName("Spring2");
        repository.save(member2);

        assertThat(repository.findAll().size()).isEqualTo(2);
    }

}
