package god.web_todo_app.Controllers;

import god.web_todo_app.DAO.UserRepository;
import god.web_todo_app.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TaskController {
    @Autowired
    UserRepository userRepository;
    public TaskController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @GetMapping("/newTask")
    public String addTask(Model model) {
        model.addAttribute("task", new Task("","",false));
        return "tasks/adding";
    }
    @PostMapping("/new")
    public String addTask(@ModelAttribute("task") Task task,Model model) {
        if (userRepository.saveTask(task.getTitle(),task.getDescription(),1)) return "redirect:/tasks";
        return "tasks/adding";
    }
    @GetMapping("/tasks")
    public String tasks(Model model) {
        model.addAttribute("tasks", userRepository.getTasks());
        System.out.println(userRepository.getTasks());
        return "tasks/tasks";
    }

    @PostMapping("/completeTask")
    public String completeTask(@ModelAttribute("task") Task task,Model model) {
        UserRepository.deleteTask(task);
        return "redirect:/tasks";
    }
}
