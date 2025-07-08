package god.web_todo_app;

import god.web_todo_app.DAO.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class test {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testGetTasks() {
        userRepository.markTaskAsCompleted(userRepository.getTasks().get(3));
    }
}