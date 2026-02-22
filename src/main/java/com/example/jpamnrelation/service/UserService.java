package com.example.jpamnrelation.service;

import com.example.jpamnrelation.dto.UserRequestDto;
import com.example.jpamnrelation.dto.UserResponseDto;
import com.example.jpamnrelation.entity.User;
import com.example.jpamnrelation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserResponseDto saveUser(UserRequestDto dto) {
        User user = new User(dto.getEmail());
        User savedUser = userRepository.save(user);

        return new UserResponseDto(
                savedUser.getId(),
                savedUser.getEmail()
        );
    }

    @Transactional(readOnly = true)
    public List<UserResponseDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponseDto> dtoList = new ArrayList<>();

        for (User user : users) {
            UserResponseDto userResponseDto = new UserResponseDto(user.getId(), user.getEmail());
            dtoList.add(userResponseDto);
        }

        return dtoList;
    }

    @Transactional(readOnly = true)
    public UserResponseDto getUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalStateException("id에 맞는 user가 존재하지 않습니다.")
        );

        return new UserResponseDto(
                user.getId(),
                user.getEmail()
        );
    }
}
