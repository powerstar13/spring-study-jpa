package spring.study.bookmanager.repository;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import spring.study.bookmanager.domain.User;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void findAllTest() {

        List<User> users = userRepository.findAll(Sort.by(Sort.Direction.DESC, "name"));
        users.forEach(System.out::println);
    }

    @Test
    void findAllByIdTest() {

        List<User> users = userRepository.findAllById(Lists.newArrayList(1L, 3L, 5L));
        users.forEach(System.out::println);
    }

    @Test
    void saveAllTest() {

        User user1 = new User("jack", "jack@gamil.com");
        User user2 = new User("steve", "steve@gamil.com");

        userRepository.saveAll(Lists.newArrayList(user1, user2));

        List<User> users = userRepository.findAll();
        users.forEach(System.out::println);
    }

    @Test
    void saveTest() {

        User user = new User("jack", "jack@gamil.com");

        userRepository.save(user);

        List<User> users = userRepository.findAll();
        users.forEach(System.out::println);
    }

    @Test
    @Transactional
    void getOneTest() {

        User user = userRepository.getOne(1L); // getOne() 메서드의 could not initialize proxy - no Session 에러를 대응하기 위해 @Transactional 애노테이션을 사용해야 함 (getOne 메서드는 LAZY fetch를 지원하고 있다.)
        System.out.println(user);
    }

    @Test
    void findByIdTest() {

        User user = userRepository.findById(1L)
            .orElse(null);
        System.out.println(user);
    }
}