package spring.study.bookmanager.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import spring.study.bookmanager.domain.Book;
import spring.study.bookmanager.domain.Publisher;
import spring.study.bookmanager.domain.Review;
import spring.study.bookmanager.domain.User;
import spring.study.bookmanager.repository.dto.BookStatus;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@Transactional
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private PublisherRepository publisherRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    void bookTest() {

        Book book = Book.builder()
            .name("Jpa 초격차 패키지")
            .authorId(1L)
            .build();

        bookRepository.save(book);

        System.out.println(bookRepository.findAll());
    }

    // ===== Relation =====

    @Test
    void bookRelationTest() {

        this.givenBookAndReview();

        User user = userRepository.findByEmail("master@gmail.com").orElseThrow(RuntimeException::new);

        System.out.println("Review: " + user.getReviews());
        System.out.println("Book: " + user.getReviews().get(0).getBook());
        System.out.println("Publisher: " + user.getReviews().get(0).getBook().getPublisher());
    }

    @Test
    void bookCascadeTest() {

        Book book = new Book();
        book.setName("JPA 초격차 패키지");

        Publisher publisher = new Publisher();
        publisher.setName("패스트캠퍼스");

        book.setPublisher(publisher);
        bookRepository.save(book);

        System.out.println("books: " + bookRepository.findAll());
        System.out.println("publishers: " + publisherRepository.findAll());

        Book book1 = bookRepository.findById(1L).get();
        book1.getPublisher().setName("슬로우캠퍼스");

        bookRepository.save(book1);

        System.out.println("publishers: " + publisherRepository.findAll());

        bookRepository.deleteById(1L);

        System.out.println("books: " + bookRepository.findAll());
        System.out.println("publishers: " + publisherRepository.findAll());
    }

    @Test
    void bookRemoveCascadeTest() {

        bookRepository.deleteById(1L);

        System.out.println("books: " + bookRepository.findAll());
        System.out.println("publishers: " + publisherRepository.findAll());

        bookRepository.findAll().forEach(book -> System.out.println(book.getPublisher()));
    }

    @Test
    void bookOrphanRemovalTest() {

        Book book = new Book();
        book.setName("JPA 초격차 패키지");

        Publisher publisher = new Publisher();
        publisher.setName("패스트캠퍼스");

        book.setPublisher(publisher);
        bookRepository.save(book);

        System.out.println("books: " + bookRepository.findAll());
        System.out.println("publishers: " + publisherRepository.findAll());

        Book book1 = bookRepository.findById(1L).get();
        book1.getPublisher().setName("슬로우캠퍼스");

        bookRepository.save(book1);

        System.out.println("publishers: " + publisherRepository.findAll());

        book1.setPublisher(null);
        bookRepository.save(book1);

        System.out.println("books: " + bookRepository.findAll());
        System.out.println("publishers: " + publisherRepository.findAll());
        System.out.println("book1-publisher: " + bookRepository.findById(1L).get().getPublisher());
    }

    @Test
    void softDelete() {

        bookRepository.findAll().forEach(System.out::println);
        System.out.println(bookRepository.findById(3L));

        bookRepository.findByCategoryIsNull().forEach(System.out::println);

        bookRepository.findAllByDeletedFalse().forEach(System.out::println);
        bookRepository.findByCategoryIsNullAndDeletedFalse().forEach(System.out::println);
    }

    @Test
    void queryTest() {

        bookRepository.findAll().forEach(System.out::println);

        System.out.println("findByCategoryIsNullAndNameEqualsAndCreatedAtGreaterThanEqualAndUpdatedAtGreaterThanEqual: " +
            bookRepository.findByCategoryIsNullAndNameEqualsAndCreatedAtGreaterThanEqualAndUpdatedAtGreaterThanEqual(
                "JPA 초격차 패키지",
                LocalDateTime.now().minusDays(1L),
                LocalDateTime.now().minusDays(1L)
            )
        );

        System.out.println("findByNameRecently: " +
            bookRepository.findByNameRecently(
                "JPA 초격차 패키지",
                LocalDateTime.now().minusDays(1L),
                LocalDateTime.now().minusDays(1L)
            )
        );

        System.out.println(bookRepository.findBookNameAndCategory());

        bookRepository.findBookNameAndCategory().forEach(b -> System.out.println(b.getName() + ": " + b.getCategory()));

        bookRepository.findBookNameAndCategory(PageRequest.of(1, 1)).forEach(bookNameAndCategory -> System.out.println(bookNameAndCategory.getName() + ": " + bookNameAndCategory.getCategory()));
        bookRepository.findBookNameAndCategory(PageRequest.of(0, 1)).forEach(bookNameAndCategory -> System.out.println(bookNameAndCategory.getName() + ": " + bookNameAndCategory.getCategory()));
    }

    @Test
    void nativeQueryTest() {

        List<Book> books = bookRepository.findAll();

        for (Book book : books) {
            book.setCategory("IT전문서");
        }

        bookRepository.saveAll(books);

        System.out.println(bookRepository.findAll());

        System.out.println("affected rows:" + bookRepository.updateCategories());
        bookRepository.findAllCustom().forEach(System.out::println);

        System.out.println(bookRepository.showTables());
    }

    @Test
    void converterTest() {

        bookRepository.findAll().forEach(System.out::println);

        Book book = new Book();
        book.setName("또 다른 IT전문서적");
        book.setStatus(new BookStatus(200));

        bookRepository.save(book);

        System.out.println(bookRepository.findRawRecord().values());
    }

    private void givenBookAndReview() {
        this.givenReview();
    }

    private Publisher givenPublisher() {

        Publisher publisher = Publisher.builder()
            .name("패스트캠퍼스")
            .build();

        return publisherRepository.save(publisher);
    }

    private Book givenBook() {

        Book book = Book.builder()
            .name("JPA 초격차 패키지")
            .publisher(this.givenPublisher())
            .build();

        return bookRepository.save(book);
    }

    private User givenUser() {
        return userRepository.findByEmail("master@gmail.com").orElseThrow(RuntimeException::new);
    }

    private void givenReview() {

        Review review = Review.builder()
            .title("내 인생을 바꾼 책")
            .content("너무너무 재미있고 즐거운 책이었어요.")
            .score(5.0F)
            .user(this.givenUser())
            .book(this.givenBook())
            .build();

        reviewRepository.save(review);
    }
}