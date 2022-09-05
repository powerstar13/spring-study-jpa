package spring.study.bookmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.study.bookmanager.domain.UserHistory;

@Repository
public interface UserHistoryRepository extends JpaRepository<UserHistory, Long> {
}
