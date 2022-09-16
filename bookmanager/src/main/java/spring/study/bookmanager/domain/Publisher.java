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
public class Publisher extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Builder.Default
    @OneToMany(orphanRemoval = true) // 고아제거속성은 CascadeType.REMOVE와는 다르게 작동한다. orphanRemoval이 true이면 부모 엔티티에서 자식 엔티티를 삭제할 경우 처리되도록 만든다.
    @JoinColumn(name = "publisher_id") // JOIN을 어떠한 컬럼으로 매핑할지 name 속성으로 명시한다.
    @ToString.Exclude
    private List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        this.books.add(book);
    }
}
