package ru.clevertec.videohostingchannels.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.videohostingchannels.dto.channel.ChannelRequest;
import ru.clevertec.videohostingchannels.dto.channel.ChannelResponse;
import ru.clevertec.videohostingchannels.exception.NotFoundException;
import ru.clevertec.videohostingchannels.exception.UniqueException;
import ru.clevertec.videohostingchannels.mapper.ChannelMapper;
import ru.clevertec.videohostingchannels.repository.ChannelRepository;
import ru.clevertec.videohostingchannels.repository.UserRepository;
import ru.clevertec.videohostingchannels.service.ChannelService;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChannelServiceImpl implements ChannelService {

    private final ChannelRepository channelRepository;
    private final UserRepository userRepository;
    private final ChannelMapper channelMapper;

    @Override
    @Transactional
    public ChannelResponse save(ChannelRequest request) {
        return userRepository.findById(request.authorId())
                .map(user -> {
                    try {
                        return channelMapper.toResponse(channelRepository.save(channelMapper.fromRequest(request)), user);
                    } catch (DataIntegrityViolationException e) {
                        throw new UniqueException("Channel with name %s is already exist".formatted(request.name()));
                    }
                })
                .orElseThrow(() -> new NotFoundException("User wit author_id %s is not found".formatted(request.authorId())));
    }

}
