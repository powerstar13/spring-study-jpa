package spring.study.bookmanager.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spring.study.bookmanager.domain.Book;
import spring.study.bookmanager.domain.BookReviewInfo;

@SpringBootTest
class BookReviewInfoRepositoryTest {

    @Autowired
    private BookReviewInfoRepository bookReviewInfoRepository;
    @Autowired
    private BookRepository bookRepository;

    @Test
    void crudTest() {

        this.givenBookReviewInfo();
    }

    @Test
    void crudTest2() {

        this.givenBookReviewInfo();

        Book result = bookReviewInfoRepository.findById(1L)
            .orElseThrow(RuntimeException::new)
            .getBook();

        System.out.println(">>> " + result);

        BookReviewInfo result2 = bookRepository.findById(1L)
            .orElseThrow(RuntimeException::new)
            .getBookReviewInfo();

        System.out.println(">>> " + result2);
    }

    private Book givenBook() {

        Book book = Book.builder()
            .name("Jpa 초격차 패키지")
            .authorId(1L)
            .build();

        return bookRepository.save(book);
    }

    private void givenBookReviewInfo() {

        BookReviewInfo bookReviewInfo = BookReviewInfo.builder()
            .book(givenBook())
            .averageReviewScore(4.5F)
            .reviewCount(2)
            .build();

        bookReviewInfoRepository.save(bookReviewInfo);

        System.out.println(bookReviewInfoRepository.findAll());
    }
}