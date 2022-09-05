package spring.study.bookmanager.domain;

import org.junit.jupiter.api.Test;

class UserTest {

    @Test
    void test() {

        User user = User.builder()
            .name("master")
            .email("master@gmail.com")
            .build();

        System.out.println(user);
    }
}