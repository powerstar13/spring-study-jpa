package spring.study.bookmanager.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import spring.study.bookmanager.domain.Review;

import java.util.List;

@SpringBootTest
class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    @Transactional
    void reviewTest() {

        List<Review> reviews1 = reviewRepository.findAll();
        reviews1.forEach(System.out::println);

        List<Review> reviews2 = reviewRepository.findAllByFetchJoin();
        reviews2.forEach(System.out::println);

        List<Review> reviews3 = reviewRepository.findAllByEntityGraph();
        reviews3.forEach(System.out::println);
    }
}