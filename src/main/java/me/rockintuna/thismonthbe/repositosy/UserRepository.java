package me.rockintuna.thismonthbe.repositosy;

import me.rockintuna.thismonthbe.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
