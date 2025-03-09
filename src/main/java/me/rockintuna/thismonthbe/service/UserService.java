package me.rockintuna.thismonthbe.service;

import lombok.RequiredArgsConstructor;
import me.rockintuna.thismonthbe.dto.UserResponseDto;
import me.rockintuna.thismonthbe.repositosy.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDto getUser(Long id) {
        return UserResponseDto.of(userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User with id " + id + " not found")
        ));
    }
}
