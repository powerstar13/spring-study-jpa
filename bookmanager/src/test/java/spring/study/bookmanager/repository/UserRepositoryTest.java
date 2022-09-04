package spring.study.bookmanager.repository;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import spring.study.bookmanager.domain.User;

import javax.transaction.Transactional;
import java.util.List;

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

    @Test
    void flushTest() {

        User user = new User("new martin", "newmartin@gamil.com");

        userRepository.save(user);

        userRepository.flush();

        userRepository.findAll().forEach(System.out::println);
    }

    @Test
    void saveAndFlushTest() {

        User user = new User("new martin", "newmartin@gamil.com");

        userRepository.saveAndFlush(user);

        userRepository.findAll().forEach(System.out::println);
    }

    @Test
    void countTest() {

        long count = userRepository.count();

        System.out.println(count);
    }

    @Test
    void existsByIdTest() {

        boolean exists = userRepository.existsById(1L); // COUNT 쿼리가 동작하는 것을 확인할 수 있음

        System.out.println(exists);
    }

    @Test
    void deleteTest() {

        userRepository.delete(userRepository.findById(1L).orElseThrow(RuntimeException::new)); // SELECT를 하고 DELETE 하는 delete() 메서드
        userRepository.findAll().forEach(System.out::println);
    }

    @Test
    void deleteByIdTest() {

        userRepository.deleteById(1L); // SELECT를 하고 DELETE 하는 deleteById() 메서드
        userRepository.findAll().forEach(System.out::println);
    }

    @Test
    void deleteAllTest() {

        userRepository.deleteAll(); // SELECT를 하고 DELETE 하는 deleteAll() 메서드
        userRepository.findAll().forEach(System.out::println);
    }

    @Test
    void deleteAllByIdTest() {

        userRepository.deleteAll(userRepository.findAllById(Lists.newArrayList(1L, 3L))); // SELECT를 하고 DELETE 하는 deleteAllById() 메서드
        userRepository.findAll().forEach(System.out::println);
    }

    @Test
    void deleteInBatchTest() {

        userRepository.deleteInBatch(userRepository.findAllById(Lists.newArrayList(1L, 3L))); // 바로 DELETE 하는 deleteInBatch() 메서드
        userRepository.findAll().forEach(System.out::println);
    }

    @Test
    void deleteAllInBatchTest() {

        userRepository.deleteAllInBatch(); // 바로 DELETE 하는 deleteAllInBatch() 메서드
        userRepository.findAll().forEach(System.out::println);
    }

    @Test
    void pageTest() {

        Page<User> users = userRepository.findAll(PageRequest.of(1, 3));

        System.out.println("page = " + users);
        System.out.println("totalElements = " + users.getTotalElements()); // 전체 요소 개수
        System.out.println("totalPages = " + users.getTotalPages()); // 전체 페이지 수
        System.out.println("numberOfElements = " + users.getNumberOfElements()); // 현재 페이지의 요소 개수
        System.out.println("sort = " + users.getSort());
        System.out.println("size = " + users.getSize()); // 페이지 당 보여줄 개수

        users.getContent().forEach(System.out::println);
    }

    @Test
    void queryByExampleWithMatcherTest() {

        ExampleMatcher matcher = ExampleMatcher.matching()
            .withIgnorePaths("name") // 매칭하지 않겠다.
            .withMatcher("email", ExampleMatcher.GenericPropertyMatchers.endsWith());

        Example<User> example = Example.of(new User("ma", "gmail.com"), matcher); // LIKE절을 사용하는 of() 메서드

        userRepository.findAll(example).forEach(System.out::println);
    }

    @Test
    void queryByExampleTest() {

        Example<User> example = Example.of(new User("master", "master@gmail.com")); // LIKE절을 사용하는 of() 메서드

        userRepository.findAll(example).forEach(System.out::println);
    }

    @Test
    void queryByExampleWithMatcher2Test() {

        User user = new User();
        user.setEmail("test");

        ExampleMatcher matcher = ExampleMatcher.matching()
            .withMatcher("email", ExampleMatcher.GenericPropertyMatchers.contains());

        Example<User> example = Example.of(user, matcher); // LIKE절을 사용하는 of() 메서드

        userRepository.findAll(example).forEach(System.out::println);
    }
}