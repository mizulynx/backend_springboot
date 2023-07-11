package mizulynx.app.repository;

import jakarta.annotation.PostConstruct;
import mizulynx.app.model.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    private final List<User> userList = new ArrayList<>();


    public UserRepository(){}
    public List<User> findAll() {
        return userList;
    }
    public Optional<User> findByUsername(String username) {
        return userList.stream().filter(c -> c.username().equals(username)).findAny();
    }

    @PostConstruct
    private void init() {
        User u = new User(
                1,
                "mizu",
                "123456",
                Bundle.NONE,
                UserStatus.REGISTERED,
                Role.ADMIN
        );
        userList.add(u);

    }
    public void save(User user) {
        userList.removeIf(c -> c.username().equals((user.username())));
        userList.add(user);
    }
    public  boolean existByUsername(String username){
        return userList.stream().filter(c -> c.id().equals(username)).count() == 1;
    }
    public void delete(String username){
        userList.removeIf(c -> c.username().equals((username)));
    }

}
