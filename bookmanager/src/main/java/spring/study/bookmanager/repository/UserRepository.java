package spring.study.bookmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.study.bookmanager.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
