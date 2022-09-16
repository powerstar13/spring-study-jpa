package spring.study.bookmanager.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
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

    @Query(value = "SELECT * FROM book", nativeQuery = true) // Native Query의 경우에는 JPQL을 사용하지 못한다. DB에서 사용하는 SQL Query를 그대로 사용하게 된다. Dialect를 사용하지 않기 때문에 특정 DB에 의존적인 Query를 작성하게 된다.
    List<Book> findAllCustom();

    @Transactional // 이처럼 인터페이스에 바로 사용하지 않고 사용하는 쪽에 트랜잭션 애노테이션을 사용해도 된다. 취향에 따라 사용하면 된다.
    @Modifying // 결과값을 int로 return하기 위해 사용
    @Query(value = "UPDATE book SET category = 'IT전문서'", nativeQuery = true)
    int updateCategories();

    @Query(value = "SHOW TABLES", nativeQuery = true)
    List<String> showTables();
}
