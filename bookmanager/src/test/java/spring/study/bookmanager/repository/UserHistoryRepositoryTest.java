package spring.study.bookmanager.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spring.study.bookmanager.domain.Gender;
import spring.study.bookmanager.domain.User;
import spring.study.bookmanager.domain.UserHistory;

import java.util.List;

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

    // ===== Relation =====

    @Test
    void userRelation() {

        User user = User.builder()
            .name("david")
            .email("david@gmail.com")
            .gender(Gender.MALE)
            .build();

        userRepository.save(user);

        user.setName("daniel");
        userRepository.save(user);

        user.setEmail("daniel@gmail.com");
        userRepository.save(user);

        List<UserHistory> result = userRepository.findByEmail("daniel@gmail.com")
            .orElseThrow(RuntimeException::new)
            .getUserHistories();

        result.forEach(System.out::println);

        System.out.println("UserHistory.getUser(): " + userHistoryRepository.findAll().get(0).getUser());
    }
}