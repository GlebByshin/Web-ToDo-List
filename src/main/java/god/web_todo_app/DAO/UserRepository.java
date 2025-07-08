package god.web_todo_app.DAO;

import god.web_todo_app.models.Task;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class UserRepository {
    private static JdbcTemplate jdbcTemplate;


    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean saveUser(String email, String password,String firstName) {
        try {
            String sql = "INSERT INTO users (email, password, firstName) VALUES (?, ?, ?)";
            jdbcTemplate.update(sql, email, password, firstName);
            return true;
        }catch (Exception e) {
            return false;
        }
    }
    public boolean saveTask(String title, String description,Integer userId) {
        try {
            String sql = "INSERT INTO tasks (name, `desc`, ownerID,completed) VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(sql,title, description, userId, false);
            return true;
        }catch (Exception e) {
            return false;
        }
    }


    public ArrayList<Task> getTasks() {
        String sql = "SELECT * FROM tasks";
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            jdbcTemplate.query(sql, (rs, rowNum) -> {
                Task task = new Task();
                task.setTitle(rs.getString("name"));
                task.setDescription(rs.getString("desc"));
                task.setCompleted(rs.getBoolean("completed"));
                tasks.add(task);
                return task;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public static void deleteTask(Task task) {
        try {
            String sql = "delete from tasks where id = ?";
            jdbcTemplate.update(sql, task.getId());
        }catch (Exception e) {
        }
    }
}
