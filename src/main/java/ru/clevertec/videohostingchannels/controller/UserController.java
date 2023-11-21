package ru.clevertec.videohostingchannels.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.videohostingchannels.dto.user.UserRequest;
import ru.clevertec.videohostingchannels.dto.user.UserResponse;
import ru.clevertec.videohostingchannels.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> save(@RequestBody UserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateById(@PathVariable Long id, @RequestBody UserRequest request) {
        return ResponseEntity.ok(userService.updateById(id, request));
    }

}
