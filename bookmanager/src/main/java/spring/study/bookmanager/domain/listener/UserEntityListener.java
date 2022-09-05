package spring.study.bookmanager.domain.listener;

import spring.study.bookmanager.domain.User;
import spring.study.bookmanager.domain.UserHistory;
import spring.study.bookmanager.repository.UserHistoryRepository;
import spring.study.bookmanager.support.BeanUtil;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class UserEntityListener {

    @PrePersist
    @PreUpdate
    public void prePersistAndPreUpdate(Object o) {

        UserHistoryRepository userHistoryRepository = BeanUtil.getBean(UserHistoryRepository.class);

        User user = (User) o;

        UserHistory userHistory = UserHistory.builder()
            .userId(user.getId())
            .name(user.getName())
            .email(user.getEmail())
            .build();

        userHistoryRepository.save(userHistory);
    }
}
