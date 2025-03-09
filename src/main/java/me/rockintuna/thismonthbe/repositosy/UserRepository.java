package me.rockintuna.thismonthbe.repositosy;

import me.rockintuna.thismonthbe.domain.User;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class UserRepository {
    private Set<User> users;

    public UserRepository() {
        this.users = new HashSet<>();
        users.add(new User(1L, "이정인"));
        users.add(new User(2L, "이수진"));
    }

    public List<User> getUsers() {
        return users.stream().toList();
    }
}
