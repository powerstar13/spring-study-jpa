package spring.study.bookmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import spring.study.bookmanager.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Modifying
    @Query(value = "UPDATE book SET category = 'none'", nativeQuery = true)
    void update();
}
