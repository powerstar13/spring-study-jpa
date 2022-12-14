package spring.study.bookmanager.repository;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import spring.study.bookmanager.domain.Address;
import spring.study.bookmanager.domain.Gender;
import spring.study.bookmanager.domain.User;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
@Transactional
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserHistoryRepository userHistoryRepository;
    @Autowired
    private EntityManager entityManager;

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
    void getOneTest() {

        User user = userRepository.getOne(1L); // getOne() 메서드의 could not initialize proxy - no Session 에러를 대응하기 위해 애노테이션을 사용해야 함 (getOne 메서드는 LAZY fetch를 지원하고 있다.)
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

    @Test
    void updateTest() {

        userRepository.save(new User("david", "david@gmail.com"));

        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user.setEmail("master-update@gmail.com");

        userRepository.save(user); // SELECT -> UPDATE 순으로 작동하는 save() 메서드를 이용한 UPDATE
    }

    // ===== QueryMethod 활용 =====

    @Test
    void select() {

        userRepository.findAllByName("master").forEach(System.out::println);

        System.out.println("findByEmail(): " + userRepository.findByEmail("master@gmail.com").orElseThrow(RuntimeException::new));
        System.out.println("getByEmail(): " + userRepository.getByEmail("master@gmail.com").orElseThrow(RuntimeException::new));
        System.out.println("readByEmail(): " + userRepository.readByEmail("master@gmail.com").orElseThrow(RuntimeException::new));
        System.out.println("queryByEmail(): " + userRepository.queryByEmail("master@gmail.com").orElseThrow(RuntimeException::new));
        System.out.println("searchByEmail(): " + userRepository.searchByEmail("master@gmail.com").orElseThrow(RuntimeException::new));
        System.out.println("streamByEmail(): " + userRepository.streamByEmail("master@gmail.com").orElseThrow(RuntimeException::new));
        System.out.println("findUserByEmail(): " + userRepository.findUserByEmail("master@gmail.com").orElseThrow(RuntimeException::new));
        System.out.println("findSomethingByEmail(): " + userRepository.findSomethingByEmail("master@gmail.com").orElseThrow(RuntimeException::new));

        System.out.println("findFirst1ByName(): " + userRepository.findFirst1ByName("master").orElseThrow(RuntimeException::new));
        System.out.println("findTop1ByName(): " + userRepository.findTop1ByName("master").orElseThrow(RuntimeException::new));
    }

    @Test
    void select2() {

        System.out.println("findAllByEmailAndName(): " + userRepository.findAllByEmailAndName("master@gmail.com", "master"));
        System.out.println("findAllByEmailOrName(): " + userRepository.findAllByEmailOrName("master@gmail.com", "master"));

        System.out.println("findAllByCreatedAtAfter(): " + userRepository.findAllByCreatedAtAfter(LocalDateTime.now().minusDays(1L)));
        System.out.println("findAllByIdAfter(): " + userRepository.findAllByIdAfter(4L));
        System.out.println("findAllByCreatedAtGreaterThan(): " + userRepository.findAllByCreatedAtGreaterThan(LocalDateTime.now().minusDays(1L)));
        System.out.println("findAllByCreatedAtGreaterThanEqual(): " + userRepository.findAllByCreatedAtGreaterThanEqual(LocalDateTime.now().minusDays(1L)));
        System.out.println("findAllByCreatedAtBetween(): " + userRepository.findAllByCreatedAtBetween(LocalDateTime.now().minusDays(1L), LocalDateTime.now().plusDays(1L)));
        System.out.println("findAllByIdBetween(): " + userRepository.findAllByIdBetween(1L, 3L));
        System.out.println("findAllByIdGreaterThanEqualAndIdLessThanEqual(): " + userRepository.findAllByIdGreaterThanEqualAndIdLessThanEqual(1L, 3L));
    }

    @Test
    void select3() {

        System.out.println("findAllByIdIsNotNull(): " + userRepository.findAllByIdIsNotNull());

        System.out.println("findAllByNameIn(): " + userRepository.findAllByNameIn(Lists.newArrayList("master", "dennis")));

        System.out.println("findAllByNameStartingWith(): " + userRepository.findAllByNameStartingWith("mas"));
        System.out.println("findAllByNameEndingWith(): " + userRepository.findAllByNameEndingWith("ter"));
        System.out.println("findAllByNameContains(): " + userRepository.findAllByNameContains("ast"));

        System.out.println("findAllByNameLike(): " + userRepository.findAllByNameLike("mas%")); // StartingWith
        System.out.println("findAllByNameLike(): " + userRepository.findAllByNameLike("%ter")); // EndingWith
        System.out.println("findAllByNameLike(): " + userRepository.findAllByNameLike("%ast%")); // Contains

        System.out.println("findAllByName(): " + userRepository.findAllByName("master"));
        System.out.println("findAllByNameIs(): " + userRepository.findAllByNameIs("master"));
        System.out.println("findUserByName(): " + userRepository.findUserByName("master"));
        System.out.println("findAllByNameEquals(): " + userRepository.findAllByNameEquals("master"));
    }

    @Test
    void sortingTest() {

        System.out.println("findTop1ByName(): " + userRepository.findTop1ByName("master").orElseThrow(RuntimeException::new));
        System.out.println("findTop1ByNameOrderByIdDesc(): " + userRepository.findTop1ByNameOrderByIdDesc("master").orElseThrow(RuntimeException::new));
        System.out.println("findFirst1ByNameOrderByIdDescEmailAsc(): " + userRepository.findFirst1ByNameOrderByIdDescEmailAsc("master").orElseThrow(RuntimeException::new));
        System.out.println("findFirstByNameWithSortParams(): " + userRepository.findFirstByName("master", Sort.by(Sort.Order.desc("id"), Sort.Order.asc("email"))).orElseThrow(RuntimeException::new));
    }

    @Test
    void pagingTest() {

        System.out.println("findByNameWithPaging(): " + userRepository.findByName("master", PageRequest.of(0, 1, Sort.by(Sort.Order.desc("id")))).getContent()); // page는 zero index부터 시작한다.
        System.out.println("findByNameWithPaging(): " + userRepository.findByName("master", PageRequest.of(0, 1, Sort.by(Sort.Order.desc("id")))).getTotalElements());
    }

    @Test
    void insertAndUpdateTest() {

        User user = new User();

        user.setName("master");
        user.setEmail("master2@gmail.com");

        User savedUser = userRepository.save(user);

        User foundUser = userRepository.findById(savedUser.getId()).orElseThrow(RuntimeException::new);
        foundUser.setName("maaaaaaster");

        userRepository.save(foundUser);
    }

    @Test
    void enumTest() {

        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user.setGender(Gender.MALE);

        userRepository.save(user);

        userRepository.findAll().forEach(System.out::println);

        System.out.println(userRepository.findRawRecord().get("gender"));
    }

    // ===== Entity의 Listener 활용하기 =====

    @Test
    void listenerTest() {

        User user = new User("master", "master2@gmail.com");
        userRepository.save(user);

        userRepository.save(user);

        User foundUser = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        foundUser.setName("maaaaaaster");

        userRepository.save(foundUser);

        userRepository.deleteById(4L);
    }

    @Test
    void prePersistTest() {

        User user = User.builder()
            .name("master")
            .email("master2@gmail.com")
            .build();

        userRepository.save(user);

        System.out.println(userRepository.findByEmail("master2@gmail.com"));
    }

    @Test
    void preUpdateTest() {

        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        System.out.println("as-is: " + user);

        user.setName("maaaaaster");
        userRepository.save(user);

        System.out.println("to-be: " + userRepository.findAll().get(0));
    }

    // ===== Embedded 활용하기 =====

    @Test
    void embedTest() {

        User user = new User();
        user.setName("steve");
        user.setHomeAddress(new Address("서울시", "강남구", "강남대로 123 집", "01234"));
        user.setCompanyAddress(new Address("서울시", "강남구", "강남대로 456 회사빌딩", "05678"));

        userRepository.save(user);

        User user1 = new User();
        user1.setName("joshua");
        user1.setHomeAddress(null);
        user1.setCompanyAddress(null);

        userRepository.save(user1);

        User user2 = new User();
        user2.setName("jordan");
        user2.setHomeAddress(new Address());
        user2.setCompanyAddress(new Address());

        userRepository.save(user2);

        entityManager.clear();

        userRepository.findAll().forEach(System.out::println);
        userHistoryRepository.findAll().forEach(System.out::println);

        userRepository.findAllRawRecord().forEach(a -> System.out.println(a.values()));

        assertAll(
            () -> assertThat(userRepository.findById(7L).get().getHomeAddress()).isNull(),
//            () -> assertThat(userRepository.findById(8L).get().getHomeAddress()).isInstanceOf(Address.class)
            () -> assertThat(userRepository.findById(8L).get().getHomeAddress()).isNull()
        );
    }
}