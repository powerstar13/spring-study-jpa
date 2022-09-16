package spring.study.bookmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.study.bookmanager.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
