package spring.study.bookmanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import spring.study.bookmanager.domain.Author;
import spring.study.bookmanager.domain.Book;
import spring.study.bookmanager.repository.AuthorRepository;
import spring.study.bookmanager.repository.BookRepository;

import javax.persistence.EntityManager;

@Service
@RequiredArgsConstructor
public class BookService {
    
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final EntityManager entityManager;

    public void put() {
        this.putBookAndAuthor(); // putBookAndAuthor() 메서드의 @Transactional 애노테이션을 무시하게 된다.
    }

    @Transactional(rollbackFor = Exception.class) // rollbackFor 옵션을 사용하여 rollbackOn() 메서드 안에 들어갈 수 있도록 등록한다.
    public void putBookAndAuthor() {
    
        Book book = new Book();
        book.setName("JPA 시작하기");
    
        bookRepository.save(book);
    
        Author author = new Author();
        author.setName("master");
    
        authorRepository.save(author);

        throw new RuntimeException("오류가 나서 DB commit이 발생하지 않습니다."); // UncheckedExcpetion는 Transactional이 있으면 rollback 처리됨
//        throw new Exception("오류가 나서 DB commit이 발생하지 않습니다."); // CheckedException는 try-catch를 통해 핸들링을 강제한다. (Transactional이 있더라도 rollback되지 않음)
    }

//    @Transactional(isolation = Isolation.READ_UNCOMMITTED) // Dirty Read로 커밋되지 않은 것도 읽어오며, 일반적으로는 많이 사용하지 않는다. @DynamicUpdate 애노테이션을 사용하여 보완해야 함 (잘 사용하지 않으며, 특별한 상황에서만 사용함)
//    @Transactional(isolation = Isolation.READ_COMMITTED) // Dirty Read 현상은 사라지게 된다. @DynamicUpdate 애노테이션을 사용하지 않아도 된다. 반복적인 조회를 했을 때 값이 변경되는 현상을 Unrepeatable 상태라고 하며, 이 현상을 방지하기 위해 나온 격리 단계가 REPEATABLE_READ 이다.
    @Transactional(isolation = Isolation.REPEATABLE_READ) // 트랜잭션 내에서 반복적인 조회를 하더라도 항상 동일한 값이 리턴되는 것을 보장한다. 팬텀 리드 현상이 존재하기 때문에 SERIALIZABLE 격리 단계가 나왔다.
//    @Transactional(isolation = Isolation.SERIALIZABLE) // 커밋이 일어나지 않은 트랜잭션이 존재하면 Lock을 통해서 대기하게 된다. 커밋이 실행되어야만 추가적인 로직이 실행된다. 웨이팅이 길어지면 성능에 좋지 않다. (잘 사용하지 않으며, 특별한 상황에서만 사용함)
    public void get(Long id) {

        System.out.println(">>> " + bookRepository.findById(id));
        System.out.println(">>> " + bookRepository.findAll());

        entityManager.clear(); // Entity 캐시를 비워주어 다음 Query에 영향을 안끼치도록 처리

        System.out.println(">>> " + bookRepository.findById(id));
        System.out.println(">>> " + bookRepository.findAll());

        bookRepository.update();

        entityManager.clear();

        Book book = bookRepository.findById(id).get();
        book.setName("바뀔까?");
        bookRepository.save(book);
    }
}
