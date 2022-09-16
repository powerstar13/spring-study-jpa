package spring.study.bookmanager.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spring.study.bookmanager.domain.Book;
import spring.study.bookmanager.repository.AuthorRepository;
import spring.study.bookmanager.repository.BookRepository;

@SpringBootTest
class BookServiceTest {
    
    @Autowired
    private BookService bookService;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    
    @Test
    void transactionTest() {

        try {
            bookService.putBookAndAuthor();
        } catch (RuntimeException e) {
            System.out.println(">>> " + e.getMessage());
        }
    
        System.out.println("books: " + bookRepository.findAll());
        System.out.println("authors: " + authorRepository.findAll());
    }

    @Test
    void transactionTest2() {

        try {
            bookService.put();
        } catch (RuntimeException e) {
            System.out.println(">>> " + e.getMessage());
        }

        System.out.println("books: " + bookRepository.findAll());
        System.out.println("authors: " + authorRepository.findAll());
    }

    @Test
    void isolationTest() {

        Book book = new Book();
        book.setName("JPA 강의");

        bookRepository.save(book);

        bookService.get(1L);

        System.out.println(">>> " + bookRepository.findAll());
    }
}