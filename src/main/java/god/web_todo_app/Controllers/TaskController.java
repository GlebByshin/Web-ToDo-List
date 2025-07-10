package god.web_todo_app.Controllers;

import god.web_todo_app.DAO.UserRepository;
import god.web_todo_app.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TaskController {

    @Autowired
    UserRepository userRepository;
    public TaskController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    private int userId = -1; // Предположим, что пользователь с ID 1

    @GetMapping("/newTask")
    public String addTask(Model model) {
        model.addAttribute("task", new Task("","",false));
        return "tasks/adding";
    }
    @PostMapping("/new")
    public String addTask(@ModelAttribute("task") Task task,Model model) {
        if (userRepository.saveTask(task.getTitle(),task.getDescription(),AuthController.getUserId())) return "redirect:/tasks";
        return "tasks/adding";
    }
    @GetMapping("/tasks")
    public String tasks(Model model) {
        model.addAttribute("tasks", userRepository.getTasks(AuthController.getUserId()));
        System.out.println(userRepository.getTasks(AuthController.getUserId()));
        return "tasks/tasks";
    }

    @PostMapping("/completeTask")
    public String completeTask(@RequestParam("id") int id,
                               @RequestParam("completed") boolean completed) {
        Task task = new Task(); // создать вручную, если не получаем через @ModelAttribute
        task.setId(id);
        task.setCompleted(completed);
        userRepository.markTaskAsCompleted(task);
        return "redirect:/tasks";
    }
}
