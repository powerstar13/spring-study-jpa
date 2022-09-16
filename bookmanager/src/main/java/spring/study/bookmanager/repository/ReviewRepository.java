package spring.study.bookmanager.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import spring.study.bookmanager.domain.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query(value = "SELECT DISTINCT r FROM Review r JOIN FETCH r.comments") // JPA의 N+1 이슈를 해결하는 방법 중 @Query 애노테이션을 통해 JPQL에서 JOIN FETCH를 사용할 수 있다.
    List<Review> findAllByFetchJoin();

    @EntityGraph(attributePaths = "comments") // JPA의 N+1 이슈를 해결하는 방법 중 @EntityGraph 애노테이션을 통해 atributePaths 옵션을 사용할 수도 있다.
    @Query("SELECT r FROM Review r")
    List<Review> findAllByEntityGraph();


    @Override
    @EntityGraph(attributePaths = "comments")
    List<Review> findAll(); // JPQL이 아닌 Query Method를 사용할 수 있다.
}
