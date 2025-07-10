package god.web_todo_app.Controllers;

import god.web_todo_app.DAO.UserRepository;
import god.web_todo_app.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {
    private static int UserId;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public String startScreen() {
        return "auth/startScreen";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("user", new User("", ""));
        return "auth/login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User("", "",""));
        return "auth/register";
    }

    @PostMapping("/addUser")
    public String login(@ModelAttribute("user")User user, Model model) {
        boolean isValid = userRepository.saveUser(user.getEmail(), user.getPassword(), user.getFirstName());
        UserId = UserRepository.getUserIdByEmail(user.getEmail());
        System.out.println("UserId: " + UserId);

        if (isValid)
            return "redirect:/tasks";
        else
            return "auth/register";
    }
    public static int getUserId() {
        return UserId;
    }
}
