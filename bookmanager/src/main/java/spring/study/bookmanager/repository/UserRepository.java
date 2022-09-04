package spring.study.bookmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.study.bookmanager.domain.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByName(String name);
    Optional<User> findByName(String name);
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
}
