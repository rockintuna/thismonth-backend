package me.rockintuna.thismonthbe.controller;

import lombok.RequiredArgsConstructor;
import me.rockintuna.thismonthbe.dto.UserRequestDto;
import me.rockintuna.thismonthbe.dto.UserResponseDto;
import me.rockintuna.thismonthbe.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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

    @PostMapping("/users")
    public ResponseEntity<Void> registerUser(
            @RequestBody UserRequestDto requestDto
    ) {
        Long id = userService.registerUser(requestDto);
        return ResponseEntity.created(URI.create("/users/" + id)).build();
    }
}
