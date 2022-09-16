package spring.study.bookmanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.study.bookmanager.domain.Comment;
import spring.study.bookmanager.repository.CommentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    @Transactional
    public void init() {

        for (int i = 0; i < 10; i++) {

            Comment comment = new Comment();
            comment.setComment("최고예요");

            commentRepository.save(comment);
        }
    }

    @Transactional(readOnly = true) // 영속성 컨텍스트는 별도의 save() 메서드가 없더라도 Dirty Check를 통해 DB를 업데이트해주는 기능이 있다. readOnly 기능을 이용하면 Dirty Check 자체가 Skip 하게 된다.
    public void updateSomething() {

        List<Comment> comments = commentRepository.findAll();

        for (Comment comment : comments) {

            comment.setComment("별로예요");

//            commentRepository.save(comment);
        }
    }

    @Transactional
    public void insertSomething() {

        Comment comment = new Comment();
        comment.setComment("이건 뭐죠?");

        commentRepository.save(comment);
    }
}
