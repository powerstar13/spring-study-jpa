package spring.study.bookmanager.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import spring.study.bookmanager.domain.Book;
import spring.study.bookmanager.repository.dto.BookNameAndCategory;

import java.time.LocalDateTime;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Modifying
    @Query(value = "UPDATE book SET category = 'none'", nativeQuery = true)
    void update();

    List<Book> findByCategoryIsNull();

    List<Book> findAllByDeletedFalse();
    List<Book> findByCategoryIsNullAndDeletedFalse();

    List<Book> findByCategoryIsNullAndNameEqualsAndCreatedAtGreaterThanEqualAndUpdatedAtGreaterThanEqual(String name, LocalDateTime createAt, LocalDateTime updatedAt);

    // JPQL 문법이라 한다. JPA의 Entity를 기반으로 한 Query이다.
    @Query(value = "SELECT b FROM Book b " +
        "WHERE name = :name AND createdAt >= :createdAt AND updatedAt >= :updatedAt AND category IS NULL")
//        "WHERE name = ?1 AND createdAt >= ?2 AND updatedAt >= ?3 AND category IS NULL") // 숫자를 기반으로 하는 것은 언제 파라미터가 변경될지 모를 상황에 대비하기 어려운 사이드 이펙트가 발생할 수 있다.
    List<Book> findByNameRecently(
        @Param("name") String name, // @Param의 값을 이용하여 @Query에 포맷팅할 수 있다.
        @Param("createdAt") LocalDateTime createAt,
        @Param("updatedAt") LocalDateTime updatedAt
    );

    @Query(value = "SELECT new spring.study.bookmanager.repository.dto.BookNameAndCategory(b.name, b.category) FROM Book b")
    List<BookNameAndCategory> findBookNameAndCategory();

    @Query(value = "SELECT new spring.study.bookmanager.repository.dto.BookNameAndCategory(b.name, b.category) FROM Book b")
    Page<BookNameAndCategory> findBookNameAndCategory(Pageable pageable);
}
