package spring.study.bookmanager.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spring.study.bookmanager.domain.User;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserHistoryRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserHistoryRepository userHistoryRepository;

    @Test
    void userHistoryTest() {

        User user = User.builder()
            .name("master-new")
            .email("master-new@gmail.com")
            .build();

        userRepository.save(user);

        user.setName("master-new-new");

        userRepository.save(user);

        userHistoryRepository.findAll().forEach(System.out::println);
    }
}