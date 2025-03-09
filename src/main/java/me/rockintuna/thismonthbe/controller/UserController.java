package me.rockintuna.thismonthbe.controller;

import lombok.RequiredArgsConstructor;
import me.rockintuna.thismonthbe.dto.UserResponseDto;
import me.rockintuna.thismonthbe.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDto>> getUser() {
        return ResponseEntity.ok(userService.getUsers());
    }
}
