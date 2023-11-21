package ru.clevertec.videohostingchannels.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.videohostingchannels.dto.user.UserRequest;
import ru.clevertec.videohostingchannels.dto.user.UserResponse;
import ru.clevertec.videohostingchannels.exception.UniqueException;
import ru.clevertec.videohostingchannels.mapper.UserMapper;
import ru.clevertec.videohostingchannels.repository.UserRepository;
import ru.clevertec.videohostingchannels.service.UserService;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserResponse save(UserRequest request) {
        try {
            return userMapper.toResponse(userRepository.save(userMapper.fromRequest(request)));
        } catch (DataIntegrityViolationException e) {
            throw new UniqueException("User with email %s is already exist".formatted(request.email()));
        }
    }

}
