package com.example.jpamnrelation.controller;

import com.example.jpamnrelation.dto.UserRequestDto;
import com.example.jpamnrelation.dto.UserResponseDto;
import com.example.jpamnrelation.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<UserResponseDto> saveUser (@RequestBody UserRequestDto dto) {
        return ResponseEntity.ok(userService.saveUser(dto));
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDto>> getAllUsers () {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserResponseDto> getUser (@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUser(userId));
    }

}
