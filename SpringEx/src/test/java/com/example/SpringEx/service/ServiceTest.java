package com.example.SpringEx.service;

import com.example.SpringEx.domain.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class ServiceTest {

    private Service service = new Service();
    @Test
    public void join() {
        // given
        Member m1 = new Member();
        m1.setName("Jiwon");

        // when
        long memberId = service.join(m1);

        // then
        Member foundMember = service.findById(memberId).get();
        assertThat(foundMember).isEqualTo(m1);

//        service.join(m1);
    }

    @Test
    public void checkDuplicateMembers() {
        // given
        Member m1 = new Member();
        m1.setName("m1입니다. ");

        Member m2 = new Member();
        m2.setName("m1입니다. ");

        // when
        service.join(m1);

        Assertions.assertThrows(IllegalStateException.class,
        () -> service.join(m2));





    }
}
