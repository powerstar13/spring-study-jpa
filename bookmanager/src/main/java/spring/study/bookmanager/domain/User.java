package spring.study.bookmanager.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity // 해당 객체가 JPA에서 관리하갈 수 있도록 명시
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor // @NoNull 애노테이션이 붙은 것을 이용하여 생성자를 만들어준다. (NonNull이 없을 경우 default 생성자와 동일해진다.)
@EntityListeners(value = MyEntityListener.class)
@Table(name = "users", indexes = { @Index(columnList = "name") }, uniqueConstraints = { @UniqueConstraint(columnNames = { "email" }) }) // user는 예약된 키워드이므로 역따옴표를 사용하여 명시하거나 다른 이름을 사용하는 것을 권장
public class User implements Auditable {

    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String email;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false)
    private LocalDateTime updatedAt;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Address> address;

    @Transient
    private String testData;

    @PrePersist // INSERT 메서드가 호출되기 전에 실행되는 메서드
    public void prePersist() {
        System.out.println(">>> prePersist");

//        this.createdAt = LocalDateTime.now();
    }
    @PostPersist // Persist 메서드가 호출되고 나서 실행되는 메서드
    public void PostPersist() {
        System.out.println(">>> PostPersist");
    }
    @PreUpdate // Merge 메서드가 호출되기 전에 실행되는 메서드
    public void PreUpdate() {
        System.out.println(">>> PreUpdate");

//        this.updatedAt = LocalDateTime.now();
    }
    @PostUpdate // Merge 메서드가 호출되고 나서 실행되는 메서드
    public void PostUpdate() {
        System.out.println(">>> PostUpdate");
    }
    @PreRemove // DELETE 메서드가 호출되기 전에 실행되는 메서드
    public void PreRemove() {
        System.out.println(">>> PreRemove");
    }
    @PostRemove // DELETE 메서드가 호출되고 나서 실행되는 메서드
    public void PostRemove() {
        System.out.println(">>> PostRemove");
    }
    @PostLoad // SELECT 조회가 호출되고 나서 실행되는 메서드
    public void PostLoad() {
        System.out.println(">>> PostLoad");
    }
}
