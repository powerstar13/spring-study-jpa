package spring.study.bookmanager.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spring.study.bookmanager.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {
    
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    
    @Test
    void test() {
        
        userService.put();
    
        System.out.println(">>> " + userRepository.findByEmail("newUser@gmail.com"));
        
        userRepository.findAll().forEach(System.out::println);
    }
}