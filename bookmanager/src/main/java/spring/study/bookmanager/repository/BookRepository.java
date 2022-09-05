package spring.study.bookmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.study.bookmanager.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
