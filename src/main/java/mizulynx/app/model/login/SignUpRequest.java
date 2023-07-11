package mizulynx.app.model.login;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SignUpRequest {
    @NotBlank
    private String username;

    @NotBlank(message = "Password must not be blank")
    @Size(min = 6, max = 12, message = "Password must be between 6 and 12 characters")
    private String password;

    // Getters and setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
