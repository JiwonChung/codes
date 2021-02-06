package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemberServiceTest {
    MemberService service;
    MemoryMemberRepository repository;

    @BeforeEach
    void beforeEach() {
        repository = new MemoryMemberRepository();
        service = new MemberService(repository);
    }
    @AfterEach
    void afterEach() {
        repository.clearStore();
    }

    @Test
    void 회원가입() {
        Member member = new Member();
        member.setName("정지원");
        service.join(member);
        Assertions.assertThrows(IllegalStateException.class,
                () -> service.join(member)
        );
    }

    @Test
    void 조회byId() {
        Member member = new Member();
        member.setName("Spring");
        service.join(member);


        Member member1 = new Member();
        member1.setName("Spring1");
        service.join(member1);

        Member member2 = new Member();
        member2.setName("Spring2");
        service.join(member2);

        Member m1 = service.findById(1);
        assertThat(m1).isEqualTo(member);
    }

    @Test
    void 조회ByName() {
        Member member = new Member();
        member.setName("Spring");
        service.join(member);


        Member member1 = new Member();
        member1.setName("Spring1");
        service.join(member1);

        Member member2 = new Member();
        member2.setName("Spring2");
        service.join(member2);

        Member spring = service.findByName("Spring");
        assertThat(spring).isEqualTo(member);
    }

    @Test
    void findAll() {
        Member member = new Member();
        member.setName("Spring");
        service.join(member);


        Member member1 = new Member();
        member1.setName("Spring1");
        service.join(member1);

        Member member2 = new Member();
        member2.setName("Spring2");
        service.join(member2);
        List<Member> all = service.findMembers();

        assertThat(all.size()).isEqualTo(3);
    }

}
