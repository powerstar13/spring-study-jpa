package spring.study.bookmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.study.bookmanager.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
