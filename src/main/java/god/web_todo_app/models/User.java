package god.web_todo_app.models;

public class User {
    private String password;
    private String email;
    private String firstName;

    public User(String password, String email, String firstName) {
        this.password = password;
        this.email = email;
        this.firstName = firstName;
    }
    public User(String password, String email) {
        this.password = password;
        this.email = email;
    }
    public User() {

    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
