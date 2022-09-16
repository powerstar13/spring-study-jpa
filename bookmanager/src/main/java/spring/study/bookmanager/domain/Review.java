package spring.study.bookmanager.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private float score;

    @ManyToOne(fetch = FetchType.LAZY) // @ManyToOne은 기본적으로 EAGER 타입이기 때문에 LAZY를 사용해야 할 경우 fetch 옵션을 사용하면 된다.
    @ToString.Exclude
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Book book;

    @OneToMany(fetch = FetchType.LAZY) // @OneToMany는 기본적으로 LAZY 타입이기 때문에 EAGER를 사용해야 할 경우 fetch 옵션을 사용하면 된다.
    @JoinColumn(name = "review_id")
    private List<Comment> comments;
}
