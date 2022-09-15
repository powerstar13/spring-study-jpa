package spring.study.bookmanager.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spring.study.bookmanager.repository.AuthorRepository;
import spring.study.bookmanager.repository.BookRepository;

import static org.junit.jupiter.api.Assertions.*;

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
        
        bookService.putBookAndAuthor();
    
        System.out.println("books: " + bookRepository.findAll());
        System.out.println("authors: " + authorRepository.findAll());
    }
}