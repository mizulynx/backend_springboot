package mizulynx.app.controller;

import jakarta.validation.Valid;
import mizulynx.app.model.*;
import mizulynx.app.model.login.SignInRequest;
import mizulynx.app.model.login.SignUpRequest;
import mizulynx.app.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserRepository repository;
    private Integer nextId = 1; // Variable to track the next available ID

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public List<User> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{username}")
    public User findById(@PathVariable String username) {
        return repository.findByUsername(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "content not found"));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void create(@Valid @RequestBody User user) {
        repository.save(user);

    }

    @PutMapping("/{username}")
    public void update(@RequestBody User user, String username) {

        if(repository.existByUsername(username)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "content not found");
        }
        repository.save(user);
    }
    @DeleteMapping("/{username}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String username){
        repository.delete(username);

    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (repository.existByUsername(signUpRequest.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists");
        }

        // Assign the next available ID to the user
        User user = new User(
                nextId++,
                signUpRequest.getUsername(),
                signUpRequest.getPassword(),
                Bundle.NONE,
                UserStatus.REGISTERED,
                Role.USER
        );

        repository.save(user);
    }

    @PostMapping("/signin")
    public User signIn(@Valid @RequestBody SignInRequest signInRequest) {
        // Retrieve the username and password from the SignInRequest object
        String username = signInRequest.getUsername();
        String password = signInRequest.getPassword();

        // Perform sign-in logic
        Optional<User> existingUser = repository.findByUsername(username);
        if (existingUser.isEmpty() || !existingUser.get().getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        }

        return existingUser.get();
    }

/*
    @PostMapping("/signin")
    public User signIn(@RequestBody User user) {
        Optional<User> existingUser = repository.findByUsername(user.getUsername());
        if (existingUser.isEmpty() || !existingUser.get().getPassword().equals(user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        }
        return existingUser.get();
    }*/

}
