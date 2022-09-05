package spring.study.bookmanager.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spring.study.bookmanager.domain.Book;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void bookTest() {

        Book book = Book.builder()
            .name("Jpa 초격차 패키지")
            .authorId(1L)
            .publisherId(1L)
            .build();

        bookRepository.save(book);

        System.out.println(bookRepository.findAll());
    }
}