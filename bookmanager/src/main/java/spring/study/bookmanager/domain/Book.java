package spring.study.bookmanager.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Book extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String category;

    private Long authorId;

    @OneToOne(mappedBy = "book") // mappedBy: 해당 필드를 Table에서 가지고 있지 않지만 연결해주기 위함으로 쓰인다.
    @ToString.Exclude // 양방향 순환 참조가 걸리는 ToString을 Exclude를 통해 풀어줄 수 있다.
    private BookReviewInfo bookReviewInfo;

    @Builder.Default
    @OneToMany
    @JoinColumn(name = "book_id")
    @ToString.Exclude
    private List<Review> reviews = new ArrayList<>();

    @ManyToOne
    @ToString.Exclude
    private Publisher publisher;
}
