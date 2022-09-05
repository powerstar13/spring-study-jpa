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

//    @ManyToMany
//    @Builder.Default
//    @ToString.Exclude
//    private List<Book> books = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "author_id")
    @Builder.Default
    @ToString.Exclude
    private List<BookAndAuthor> bookAndAuthors = new ArrayList<>();

//    public void addBook(Book... books) {
//        Collections.addAll(this.books, books);
//    }

    public void addBookAndAuthors(BookAndAuthor... bookAndAuthors) {
        Collections.addAll(this.bookAndAuthors, bookAndAuthors);
    }
}
