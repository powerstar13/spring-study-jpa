package spring.study.bookmanager.domain;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@DynamicUpdate // 필요한 정보만 UPDATE하기 때문에 불필요한 요소까지 SET하지 않는다. (Dirty Read의 경우 사용하기 좋음)
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

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}) // cascade 옵션을 통해 영속성 전이를 일으킨다. Book이 Persist, Merge, Remove가 될 때 Publisher도 Persist, Merge, Remove 해라는 의미이다.
    @ToString.Exclude
    private Publisher publisher;

//    @ManyToMany
//    @Builder.Default
//    @ToString.Exclude
//    private List<Author> authors = new ArrayList<>();


    @OneToMany
    @JoinColumn(name = "book_id")
    @Builder.Default
    @ToString.Exclude
    private List<BookAndAuthor> bookAndAuthors = new ArrayList<>();

//    public void addAuthor(Author... authors) {
//        Collections.addAll(this.authors, authors);
//    }

    public void addBookAndAuthors(BookAndAuthor... bookAndAuthors) {
        Collections.addAll(this.bookAndAuthors, bookAndAuthors);
    }
}
