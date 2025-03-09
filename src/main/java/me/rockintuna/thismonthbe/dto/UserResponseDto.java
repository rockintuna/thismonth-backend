package me.rockintuna.thismonthbe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.rockintuna.thismonthbe.domain.User;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserResponseDto {
    private String userName;

    public static UserResponseDto of(User user) {
        return new UserResponseDto(user.getUsername());
    }
}
