package ru.clevertec.videohostingchannels.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.videohostingchannels.dto.channel.ChannelRequest;
import ru.clevertec.videohostingchannels.dto.channel.ChannelResponse;
import ru.clevertec.videohostingchannels.exception.NotFoundException;
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
    public ChannelResponse saveByAuthorId(Long authorId, ChannelRequest request) {
        return userRepository.findById(authorId)
                .map(user -> channelMapper.toResponse(channelRepository.save(channelMapper.fromRequest(authorId, request))))
                .orElseThrow(() -> new NotFoundException("User with author_id %s is not found".formatted(authorId)));
    }

    @Override
    @Transactional
    public ChannelResponse updateById(Long id, ChannelRequest request) {
        return channelRepository.findById(id)
                .map(channel -> channelMapper.fromRequest
                        (channel.getId(), channel.getAuthor().getId(), channel.getCreatedAt(), request))
                .map(channelRepository::save)
                .map(channelMapper::toResponse)
                .orElseThrow(() -> new NotFoundException("Channel wit id %s is not found".formatted(id)));
    }

}
