package spring.study.bookmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.study.bookmanager.domain.BookReviewInfo;

public interface BookReviewInfoRepository extends JpaRepository<BookReviewInfo, Long> {
}
