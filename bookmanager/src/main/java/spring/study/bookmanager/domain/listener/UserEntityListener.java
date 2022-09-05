package spring.study.bookmanager.domain.listener;

import spring.study.bookmanager.domain.User;
import spring.study.bookmanager.domain.UserHistory;
import spring.study.bookmanager.repository.UserHistoryRepository;
import spring.study.bookmanager.support.BeanUtil;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;

public class UserEntityListener {

    @PostPersist
    @PostUpdate
    public void postPersistAndPostUpdate(Object o) {

        UserHistoryRepository userHistoryRepository = BeanUtil.getBean(UserHistoryRepository.class);

        User user = (User) o;

        UserHistory userHistory = UserHistory.builder()
            .name(user.getName())
            .email(user.getEmail())
            .user(user)
            .build();

        userHistoryRepository.save(userHistory);
    }
}
