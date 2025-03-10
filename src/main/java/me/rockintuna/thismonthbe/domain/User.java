package me.rockintuna.thismonthbe.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.rockintuna.thismonthbe.dto.UserRequestDto;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name="tm_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;

    public static User create(UserRequestDto requestDto) {
        return new User(null, requestDto.getName());
    }
}
