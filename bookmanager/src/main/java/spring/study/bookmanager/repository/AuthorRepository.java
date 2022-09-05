package spring.study.bookmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.study.bookmanager.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
