package ru.clevertec.videohostingchannels.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.videohostingchannels.dto.channel.ChannelNamesResponse;
import ru.clevertec.videohostingchannels.dto.user.UserRequest;
import ru.clevertec.videohostingchannels.dto.user.UserResponse;
import ru.clevertec.videohostingchannels.exception.NotFoundException;
import ru.clevertec.videohostingchannels.exception.UniqueException;
import ru.clevertec.videohostingchannels.mapper.UserMapper;
import ru.clevertec.videohostingchannels.model.Channel;
import ru.clevertec.videohostingchannels.model.Subscription;
import ru.clevertec.videohostingchannels.repository.UserRepository;
import ru.clevertec.videohostingchannels.service.UserService;

import java.util.function.Supplier;

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
            throw new UniqueException("User with name %s and email %s is already exist"
                    .formatted(request.name(), request.email()));
        }
    }

    @Override
    @Transactional
    public UserResponse updateById(Long id, UserRequest request) {
        return userRepository.findById(id)
                .map(user -> userMapper.fromRequest(user.getId(), request))
                .map(user -> {
                    try {
                        return userRepository.saveAndFlush(user);
                    } catch (DataIntegrityViolationException e) {
                        throw new UniqueException("User with name %s and email %s is already exist"
                                .formatted(request.name(), request.email()));
                    }
                })
                .map(userMapper::toResponse)
                .orElseThrow(throwNotFoundException(id));
    }

    @Override
    public ChannelNamesResponse findAllSubscribedChannelNamesById(Long id) {
        return new ChannelNamesResponse(userRepository.findWithSubscriptionsChannelById(id)
                .orElseThrow(throwNotFoundException(id))
                .getSubscriptions()
                .stream()
                .map(Subscription::getChannel)
                .map(Channel::getName)
                .toList());
    }

    private Supplier<NotFoundException> throwNotFoundException(Long id) {
        return () -> new NotFoundException("User with id %s is not found".formatted(id));
    }

}
