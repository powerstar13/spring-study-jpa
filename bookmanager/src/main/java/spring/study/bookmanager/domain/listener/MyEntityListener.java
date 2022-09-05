package spring.study.bookmanager.domain.listener;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

public class MyEntityListener {

    @PrePersist
    public void prePersist(Object o) { // Entity에서는 this 키워드를 통해 접근할 수 있지만, EntityListener를 통할 경우 Object를 받아서 제어한다.

        if (o instanceof Auditable) {
            ((Auditable) o).setCreatedAt(LocalDateTime.now());
            ((Auditable) o).setUpdatedAt(LocalDateTime.now());
        }
    }

    @PreUpdate
    public void preUpdate(Object o) {

        if (o instanceof Auditable) {
            ((Auditable) o).setUpdatedAt(LocalDateTime.now());
        }
    }
}
