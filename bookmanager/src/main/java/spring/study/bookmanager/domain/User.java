package spring.study.bookmanager.domain;

import lombok.*;
import spring.study.bookmanager.domain.listener.UserEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity // 해당 객체가 JPA에서 관리하갈 수 있도록 명시
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor // @NoNull 애노테이션이 붙은 것을 이용하여 생성자를 만들어준다. (NonNull이 없을 경우 default 생성자와 동일해진다.)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@EntityListeners(value = { UserEntityListener.class })
@Table(name = "users", indexes = { @Index(columnList = "name") }, uniqueConstraints = { @UniqueConstraint(columnNames = { "email" }) }) // user는 예약된 키워드이므로 역따옴표를 사용하여 명시하거나 다른 이름을 사용하는 것을 권장
public class User extends BaseEntity {

    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String email;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @Builder.Default
    @ToString.Exclude
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", insertable = false, updatable = false) // 잠재적인 성능 이슈를 막기 위해 개발자가 원하는 최적의 쿼리만 작동하도록 insertable과 updatable을 false 처리하여 막아준다.
    private List<UserHistory> userHistories = new ArrayList<>(); // NullPointerException을 방지하기 위해 new ArrayList<>() 초기화

    @Builder.Default
    @OneToMany
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private List<Review> reviews = new ArrayList<>();
}
