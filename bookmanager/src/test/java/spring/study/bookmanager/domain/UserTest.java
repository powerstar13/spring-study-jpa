package spring.study.bookmanager.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void test() {

        User user = User.builder()
            .name("master")
            .email("master@gmail.com")
            .createdAt(LocalDateTime.now())
            .build();

        System.out.println(user);
    }
}