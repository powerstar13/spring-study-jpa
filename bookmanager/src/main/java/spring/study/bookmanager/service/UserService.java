package spring.study.bookmanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.study.bookmanager.domain.User;
import spring.study.bookmanager.repository.UserRepository;

import javax.persistence.EntityManager;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final EntityManager entityManager;
    private final UserRepository userRepository;
    
    @Transactional
    public void put() {
    
        User user = new User();
        user.setName("newUser");
        user.setEmail("newUser@gmail.com"); // 비 영속 성태
    
        entityManager.persist(user); // 영속 상태
        
        entityManager.detach(user); // 준 영속 상태로 영속성 컨텍스트에서 관리하지 않게 한다.
        
        user.setName("newUserAfterPersist"); // 영속 상태에 있는 객체는 Transaction이 종료되는 시점에 save() 메서드를 별도로 호출하지 않아도 setter에 의해 UPDATE 쿼리가 실행되어 실제 DB에 반영된다.
        entityManager.merge(user); // detach된 객체를 영속 상태로 전환하여 영속성 컨텍스트에서 관리하도록 한다.
        
        entityManager.flush(); // clear()를 사용하기 전에 반드시 flush() 메서드를 사용하여 반영해줄 것을 권장한다.
        entityManager.clear(); // EntityManager를 비워냄으로 준 영속 상태로 만든다.
        
        User user2 = userRepository.findById(1L).get();
        entityManager.remove(user2); // 해당 객체를 삭제한다. (detach가 되면 remove를 사용할 수 없다.)
        
//        user2.setName("massster"); // 이미 remove() 메서드에 의해 삭제되었으므로 수정할 수 없다.
//        entityManager.merge(user2);
    }
}
