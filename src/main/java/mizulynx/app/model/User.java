package mizulynx.app.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record User(
        //valuyes necessary to sign up and id generated
        Integer id,
        @NotBlank
        String username,
        @NotBlank(message = "Password must not be blank")
        @Size(min = 6, max = 12, message = "Password must be between 6 and 12 characters")
        String password,

        // this traits will be automatically set to
        Bundle bundle,
        UserStatus status,
        Role role
) {
        public String getUsername() {
                return username;
        }

        public String getPassword() {
                return password;
        }
}
