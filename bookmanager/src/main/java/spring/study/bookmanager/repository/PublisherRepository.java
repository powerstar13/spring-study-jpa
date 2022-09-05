package spring.study.bookmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.study.bookmanager.domain.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
}
