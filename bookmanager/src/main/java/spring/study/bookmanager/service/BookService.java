package spring.study.bookmanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.study.bookmanager.domain.Author;
import spring.study.bookmanager.domain.Book;
import spring.study.bookmanager.repository.AuthorRepository;
import spring.study.bookmanager.repository.BookRepository;

@Service
@RequiredArgsConstructor
public class BookService {
    
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

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
}
