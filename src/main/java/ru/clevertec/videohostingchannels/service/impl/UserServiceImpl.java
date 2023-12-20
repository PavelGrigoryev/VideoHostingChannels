package ru.clevertec.videohostingchannels.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.videohostingchannels.dto.channel.ChannelNamesResponse;
import ru.clevertec.videohostingchannels.dto.user.UserRequest;
import ru.clevertec.videohostingchannels.dto.user.UserResponse;
import ru.clevertec.videohostingchannels.exception.NotFoundException;
import ru.clevertec.videohostingchannels.exception.ServiceException;
import ru.clevertec.videohostingchannels.mapper.UserMapper;
import ru.clevertec.videohostingchannels.model.Channel;
import ru.clevertec.videohostingchannels.model.Subscription;
import ru.clevertec.videohostingchannels.repository.UserRepository;
import ru.clevertec.videohostingchannels.service.UserService;

import java.util.Optional;
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
        return Optional.of(request)
                .map(userMapper::fromRequest)
                .map(userRepository::save)
                .map(userMapper::toResponse)
                .orElseThrow(() -> new ServiceException("Error to save user"));
    }

    @Override
    @Transactional
    public UserResponse updateById(Long id, UserRequest request) {
        return userRepository.findById(id)
                .map(user -> userMapper.fromRequest(user.getId(), request))
                .map(userRepository::save)
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
