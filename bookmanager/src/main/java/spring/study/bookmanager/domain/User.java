package spring.study.bookmanager.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Builder
@Entity
@Table(name = "`user`") // user는 예약된 키워드이므로 역따옴표를 사용하여 명시해야 사용 가능
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor // @NoNull 애노테이션이 붙은 것을 이용하여 생성자를 만들어준다. (NonNull이 없을 경우 default 생성자와 동일해진다.)
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String email;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
