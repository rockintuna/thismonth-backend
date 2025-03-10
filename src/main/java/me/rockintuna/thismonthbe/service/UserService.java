package me.rockintuna.thismonthbe.service;

import lombok.RequiredArgsConstructor;
import me.rockintuna.thismonthbe.domain.User;
import me.rockintuna.thismonthbe.dto.UserRequestDto;
import me.rockintuna.thismonthbe.dto.UserResponseDto;
import me.rockintuna.thismonthbe.repositosy.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDto getUser(Long id) {
        return UserResponseDto.of(userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User with id " + id + " not found")
        ));
    }

    public List<UserResponseDto> getUsers() {
        return userRepository.findAll().stream().map(UserResponseDto::of).collect(Collectors.toList());
    }

    public Long registerUser(UserRequestDto requestDto) {
        return userRepository.save(User.create(requestDto)).getId();
    }
}
