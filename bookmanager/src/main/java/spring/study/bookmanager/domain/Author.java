package spring.study.bookmanager.domain;

import lombok.*;

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
public class Author extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String country;

    @Builder.Default
    @ManyToMany
    @ToString.Exclude
    private List<Book> books = new ArrayList<>();

    public void addBook(Book... books) {
        Collections.addAll(this.books, books);
    }
}
