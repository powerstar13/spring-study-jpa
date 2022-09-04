package spring.study.bookmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.study.bookmanager.domain.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByName(String name);
    Optional<User> findByEmail(String email);
    Optional<User> getByEmail(String email);
    Optional<User> readByEmail(String email);
    Optional<User> queryByEmail(String email);
    Optional<User> searchByEmail(String email);
    Optional<User> streamByEmail(String email);
    Optional<User> findUserByEmail(String email);
    Optional<User> findSomethingByEmail(String email);
    Optional<User> findFirst1ByName(String name);
    Optional<User> findTop1ByName(String name);

    List<User> findAllByEmailAndName(String email, String name);
    List<User> findAllByEmailOrName(String email, String name);

    List<User> findAllByCreatedAtAfter(LocalDateTime yesterday); // After: 포함하지 않는
    List<User> findAllByIdAfter(Long id);
    List<User> findAllByCreatedAtGreaterThan(LocalDateTime yesterday); // Than: 포함하지 않는
    List<User> findAllByCreatedAtGreaterThanEqual(LocalDateTime yesterday); // ThanEqual: 포함하는
    List<User> findAllByCreatedAtBetween(LocalDateTime yesterday, LocalDateTime tomorrow); // Between: 포함하는
    List<User> findAllByIdBetween(Long id1, Long id2);
    List<User> findAllByIdGreaterThanEqualAndIdLessThanEqual(Long id1, Long id2); // Between을 풀어 쓴 것과 같다.

    List<User> findAllByIdIsNotNull();
    List<User> findAllByAddressIsNotEmpty(); // IsEmpty / IsNotEmpty can only be used on collection properties!

    List<User> findAllByNameIn(List<String> names);

    List<User> findAllByNameStartingWith(String name);
    List<User> findAllByNameEndingWith(String name);
    List<User> findAllByNameContains(String name);
    List<User> findAllByNameLike(String name);

    // 가독성을 제시하는 키워드 Is, Equals
    List<User> findAllByNameIs(String name);
    List<User> findUserByName(String name);
    List<User> findAllByNameEquals(String name);
}
