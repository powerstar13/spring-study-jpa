package spring.study.bookmanager.repository;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spring.study.bookmanager.domain.Author;
import spring.study.bookmanager.domain.Book;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;

    @Test
    @Transactional
    void manyToManyTest() {

        Book book1 = this.givenBook("책1");
        Book book2 = this.givenBook("책2");
        Book book3 = this.givenBook("개발책1");
        Book book4 = this.givenBook("개발책2");

        Author author1 = this.givenAuthor("master");
        Author author2 = this.givenAuthor("steve");

        book1.addAuthor(author1);
        book2.addAuthor(author2);
        book3.addAuthor(author1, author2);
        book4.addAuthor(author1, author2);

        author1.addBook(book1, book3, book4);
        author2.addBook(book2, book3, book4);

        bookRepository.saveAll(Lists.newArrayList(book1, book2, book3, book4));
        authorRepository.saveAll(Lists.newArrayList(author1, author2));

        System.out.println("authors through book: " + bookRepository.findAll().get(2).getAuthors());
        System.out.println("books through author: " + authorRepository.findAll().get(0).getBooks());
    }

    private Book givenBook(String name) {

        Book book = Book.builder()
            .name(name)
            .build();

        return bookRepository.save(book);
    }

    private Author givenAuthor(String name) {

        Author author = Author.builder()
            .name(name)
            .build();

        return authorRepository.save(author);
    }
}