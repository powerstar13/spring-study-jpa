package spring.study.bookmanager.repository;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spring.study.bookmanager.domain.Author;
import spring.study.bookmanager.domain.Book;
import spring.study.bookmanager.domain.BookAndAuthor;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookAndAuthorRepository bookAndAuthorRepository;

    @Test
    @Transactional
    void manyToManyTest() {

        Book book1 = this.givenBook("책1");
        Book book2 = this.givenBook("책2");
        Book book3 = this.givenBook("개발책1");
        Book book4 = this.givenBook("개발책2");

        Author author1 = this.givenAuthor("master");
        Author author2 = this.givenAuthor("steve");

        // M:N 방식
//        book1.addAuthor(author1);
//        book2.addAuthor(author2);
//        book3.addAuthor(author1, author2);
//        book4.addAuthor(author1, author2);
//
//        author1.addBook(book1, book3, book4);
//        author2.addBook(book2, book3, book4);

        // 1:N:1 방식 (M:N 방식을 풀어낸 것임)
        BookAndAuthor bookAndAuthor1 = this.givenBookAndAuthor(book1, author1);
        BookAndAuthor bookAndAuthor2 = this.givenBookAndAuthor(book2, author2);
        BookAndAuthor bookAndAuthor3 = this.givenBookAndAuthor(book3, author1);
        BookAndAuthor bookAndAuthor4 = this.givenBookAndAuthor(book3, author2);
        BookAndAuthor bookAndAuthor5 = this.givenBookAndAuthor(book4, author1);
        BookAndAuthor bookAndAuthor6 = this.givenBookAndAuthor(book4, author2);

        book1.addBookAndAuthors(bookAndAuthor1);
        book2.addBookAndAuthors(bookAndAuthor2);
        book3.addBookAndAuthors(bookAndAuthor3, bookAndAuthor4);
        book4.addBookAndAuthors(bookAndAuthor5, bookAndAuthor6);

        author1.addBookAndAuthors(bookAndAuthor1, bookAndAuthor3, bookAndAuthor5);
        author2.addBookAndAuthors(bookAndAuthor2, bookAndAuthor4, bookAndAuthor6);

        bookRepository.saveAll(Lists.newArrayList(book1, book2, book3, book4));
        authorRepository.saveAll(Lists.newArrayList(author1, author2));

//        System.out.println("authors through book: " + bookRepository.findAll().get(2).getAuthors());
//        System.out.println("books through author: " + authorRepository.findAll().get(0).getBooks());

        bookRepository.findAll()
            .get(2)
            .getBookAndAuthors()
            .forEach(bookAndAuthor -> System.out.println(bookAndAuthor.getAuthor()));

        authorRepository.findAll()
            .get(0)
            .getBookAndAuthors()
            .forEach(bookAndAuthor -> System.out.println(bookAndAuthor.getBook()));
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

    private BookAndAuthor givenBookAndAuthor(Book book, Author author) {

        BookAndAuthor bookAndAuthor = BookAndAuthor.builder()
            .book(book)
            .author(author)
            .build();

        return bookAndAuthorRepository.save(bookAndAuthor);
    }
}