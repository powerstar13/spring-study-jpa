package spring.study.bookmanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import spring.study.bookmanager.domain.Author;
import spring.study.bookmanager.repository.AuthorRepository;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

//    @Transactional(propagation = Propagation.REQUIRED)
    @Transactional(propagation = Propagation.REQUIRES_NEW) // 기존 트랜잭션이 있건 없건 무조건 새로운 트랜잭션을 생성시킨다. 호출한 쪽의 트랜잭션 커밋과 롤백하고 상관없이, 자체적으로 커밋과 롤백을 진행하겠다는 의미이다.
    public void putAuthor() {

        Author author = new Author();
        author.setName("master");

        authorRepository.save(author);

        throw new RuntimeException("오류가 발생했습니다. 트랜잭션은 어떻게 될까요?");
    }
}
