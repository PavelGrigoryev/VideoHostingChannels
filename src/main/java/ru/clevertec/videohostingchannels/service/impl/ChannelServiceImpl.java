package ru.clevertec.videohostingchannels.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.clevertec.videohostingchannels.dto.channel.ChannelDetailedInformationResponse;
import ru.clevertec.videohostingchannels.dto.channel.ChannelFilterResponse;
import ru.clevertec.videohostingchannels.dto.channel.ChannelRequest;
import ru.clevertec.videohostingchannels.dto.channel.ChannelResponse;
import ru.clevertec.videohostingchannels.exception.MultipartGetBytesException;
import ru.clevertec.videohostingchannels.exception.NotFoundException;
import ru.clevertec.videohostingchannels.exception.UniqueException;
import ru.clevertec.videohostingchannels.mapper.ChannelMapper;
import ru.clevertec.videohostingchannels.model.Channel;
import ru.clevertec.videohostingchannels.repository.ChannelRepository;
import ru.clevertec.videohostingchannels.repository.UserRepository;
import ru.clevertec.videohostingchannels.service.ChannelService;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChannelServiceImpl implements ChannelService {

    private final ChannelRepository channelRepository;
    private final UserRepository userRepository;
    private final ChannelMapper channelMapper;

    @Override
    @Transactional
    public ChannelResponse saveByAuthorId(Long authorId, ChannelRequest request, MultipartFile file) {
        return userRepository.findById(authorId)
                .map(user -> {
                    try {
                        return channelMapper.toResponse(channelRepository.save(channelMapper
                                .fromRequest(authorId, request, Objects.isNull(file) ? null : file.getBytes())));
                    } catch (IOException e) {
                        throw new MultipartGetBytesException("Error to extract avatar");
                    } catch (DataIntegrityViolationException e) {
                        throw new UniqueException("Channel with name %s is already exist".formatted(request.name()));
                    }
                })
                .orElseThrow(() -> new NotFoundException("User with author_id %s is not found".formatted(authorId)));
    }

    @Override
    @Transactional
    public ChannelResponse updateById(Long id, ChannelRequest request, MultipartFile file) {
        return channelRepository.findWithAuthorById(id)
                .map(channel -> {
                    try {
                        return channelMapper.fromRequest(channel.getId(), channel.getAuthor().getId(),
                                channel.getCreatedAt(), request, Objects.isNull(file) ? null : file.getBytes());
                    } catch (IOException e) {
                        throw new MultipartGetBytesException("Error to extract avatar");
                    }
                })
                .map(channel -> {
                    try {
                        return channelRepository.saveAndFlush(channel);
                    } catch (DataIntegrityViolationException e) {
                        throw new UniqueException("Channel with name %s is already exist".formatted(request.name()));
                    }
                })
                .map(channelMapper::toResponse)
                .orElseThrow(throwNotFoundException(id));
    }

    @Override
    public List<ChannelFilterResponse> findAllByFilter(String name, String language, String category, Pageable pageable) {
        return channelRepository.findAllByFilter(name, language, category, pageable)
                .stream()
                .map(channelMapper::toFilterResponse)
                .toList();
    }

    @Override
    public ChannelDetailedInformationResponse findDetailedInformationById(Long id) {
        return channelRepository.findDetailedInformationById(id)
                .map(channelMapper::toDetailedInformationResponse)
                .orElseThrow(throwNotFoundException(id));
    }

    @Override
    public byte[] downloadChannelAvatarById(Long id) {
        return channelRepository.findById(id)
                .map(Channel::getAvatar)
                .orElseThrow(() -> new NotFoundException("Avatar on channel with id %s is not found".formatted(id)));
    }

    private Supplier<NotFoundException> throwNotFoundException(Long id) {
        return () -> new NotFoundException("Channel with id %s is not found".formatted(id));
    }

}
