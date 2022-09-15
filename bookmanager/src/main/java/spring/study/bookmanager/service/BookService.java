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
    
    @Transactional
    public void putBookAndAuthor() {
    
        Book book = new Book();
        book.setName("JPA 시작하기");
    
        bookRepository.save(book);
    
        Author author = new Author();
        author.setName("master");
    
        authorRepository.save(author);
    }
}
