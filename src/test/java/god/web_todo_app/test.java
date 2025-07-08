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
        System.out.println("Testing getTasks method:");
        System.out.println(userRepository.getTasks());
        System.out.println("Number of tasks: " + userRepository.getTasks().size());
        // Можно добавить assert'ы для проверки содержимого списка задач
    }
}