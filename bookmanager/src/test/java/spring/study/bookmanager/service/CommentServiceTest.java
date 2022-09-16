package spring.study.bookmanager.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spring.study.bookmanager.repository.CommentRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentServiceTest {

    @Autowired
    private CommentService commentService;
    @Autowired
    private CommentRepository commentRepository;

    @Test
    void commentTest() {

        commentService.init();

//        commentRepository.findAll().forEach(System.out::println);

        commentService.updateSomething();
//        commentService.insertSomething();
    }
}