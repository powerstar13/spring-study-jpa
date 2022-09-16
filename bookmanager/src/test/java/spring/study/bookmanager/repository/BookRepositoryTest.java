package spring.study.bookmanager.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spring.study.bookmanager.domain.Book;
import spring.study.bookmanager.domain.Publisher;
import spring.study.bookmanager.domain.Review;
import spring.study.bookmanager.domain.User;

import javax.transaction.Transactional;

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