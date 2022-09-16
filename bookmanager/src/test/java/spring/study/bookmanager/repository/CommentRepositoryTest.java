package spring.study.bookmanager.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import spring.study.bookmanager.domain.Comment;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;

@SpringBootTest
class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private EntityManager entityManager;

    @Test
    @Transactional
    void commentTest() {

        Comment comment = new Comment();
        comment.setComment("별로예요");
//        comment.setCommentedAt(LocalDateTime.now());

        commentRepository.saveAndFlush(comment);

        entityManager.clear();

        System.out.println(comment);

        commentRepository.findAll().forEach(System.out::println);
    }
}