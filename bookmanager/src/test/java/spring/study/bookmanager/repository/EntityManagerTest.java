package spring.study.bookmanager.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import spring.study.bookmanager.domain.User;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional // 쓰기 지연을 발생시킨다.
public class EntityManagerTest {

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private UserRepository userRepository;

    @Test
    void entityManagerTest() {
        System.out.println(entityManager.createQuery("SELECT u FROM User u").getResultList()); // JPQL 방식
    }

    @Test
    void cacheFindTest() {
        System.out.println(userRepository.findByEmail("master@gmail.com"));
        System.out.println(userRepository.findByEmail("master@gmail.com"));
        System.out.println(userRepository.findByEmail("master@gmail.com"));
        // 영속성 컨텍스트에 의해 1차 캐시된다. (성능 저하를 막기 위해 사용할 수 있다.)
        // 1차 캐시는 map의 형태로 만들어진다. (Key는 id가 Value는 값이 들어간다.)
        System.out.println(userRepository.findById(2L));
        System.out.println(userRepository.findById(2L));
        System.out.println(userRepository.findById(2L));

        userRepository.deleteById(1L);

        userRepository.flush();
    }

    @Test
    void cacheFindTest2() {

        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user.setName("massster");

        userRepository.save(user);

//        userRepository.flush();

        System.out.println("------------------------------");

        user.setEmail("massster@gmail.com");

        userRepository.save(user);

        System.out.println(" 1 >>> " + userRepository.findById(1L)); // 영속성 컨텍스트에 의해 캐시된 데이터를 가져오므로 데이터가 바뀐 것 처럼 확인되지만 실제 DB에 반영은 안 된 상태이다.

        userRepository.flush(); // 모여있는 것을 비워낸다. 개발자가 원하는 타이밍에 DB에 반영되기 원할 경우 flush를 사용할 수 있다. 너무 남발하게 될 경우 영속성의 장점을 해칠 수 있으므로 적당하게 사용해야 한다.

        System.out.println(" 2 >>> " + userRepository.findById(1L));
    }

    @Test
    void cacheFindTest3() {

        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user.setName("massster");
        userRepository.save(user);

        System.out.println("------------------------------");

        user.setEmail("massster@gmail.com");
        userRepository.save(user);

        // 이 경우에는 findAll을 하기 위해 실제 DB의 값과 영속성 컨텍스트에 캐시된 것 중 어떤 것이 최신인지 비교하여 가져오기 위해 flush가 먼저 발생되고 findAll이 작동된다.
        System.out.println(userRepository.findAll()); // SELECT * FROM user
    }
}
