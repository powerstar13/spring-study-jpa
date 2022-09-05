package spring.study.bookmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.study.bookmanager.domain.BookAndAuthor;

public interface BookAndAuthorRepository extends JpaRepository<BookAndAuthor, Long> {
}
