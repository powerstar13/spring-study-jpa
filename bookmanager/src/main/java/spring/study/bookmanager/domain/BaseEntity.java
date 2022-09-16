package spring.study.bookmanager.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import spring.study.bookmanager.domain.listener.Auditable;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Data
@MappedSuperclass // 해당 클래스의 필드를 상속받는 엔터티의 필드로 포함시켜주겠다는 애노테이션
@EntityListeners(value = AuditingEntityListener.class)
public class BaseEntity implements Auditable {

    @CreatedDate
    @Column(columnDefinition = "datetime(6) default now(6) comment '생성시간'", nullable = false, updatable = false) // columnDefinition 옵션을 사용할 경우 data.sql을 이용하여 데이터를 넣을 때 default 값을 설정할 수 있다. (해당 옵션은 auto-ddl을 할 때 사용하는 것인데, 현업에서는 auto-ddl을 사용할 일이 거의 없다.) date type 부분을 치환하는 역할을 한다.
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(columnDefinition = "datetime(6) default now(6) comment '수정시간'", nullable = false)
    private LocalDateTime updatedAt;
}
