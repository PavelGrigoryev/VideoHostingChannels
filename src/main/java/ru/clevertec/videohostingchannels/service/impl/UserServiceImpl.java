package ru.clevertec.videohostingchannels.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.videohostingchannels.dto.user.UserRequest;
import ru.clevertec.videohostingchannels.dto.user.UserResponse;
import ru.clevertec.videohostingchannels.exception.NotFoundException;
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
        return userMapper.toResponse(userRepository.save(userMapper.fromRequest(request)));
    }

    @Override
    @Transactional
    public UserResponse updateById(Long id, UserRequest request) {
        return userRepository.findById(id)
                .map(user -> userMapper.fromRequest(user.getId(), request))
                .map(userRepository::save)
                .map(userMapper::toResponse)
                .orElseThrow(() -> new NotFoundException("User with id %s is not found".formatted(id)));
    }

}
