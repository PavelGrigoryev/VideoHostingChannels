package ru.clevertec.videohostingchannels.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.clevertec.videohostingchannels.dto.channel.ChannelDetailedInformationResponse;
import ru.clevertec.videohostingchannels.dto.channel.ChannelFilterResponse;
import ru.clevertec.videohostingchannels.dto.channel.ChannelRequest;
import ru.clevertec.videohostingchannels.dto.channel.ChannelResponse;
import ru.clevertec.videohostingchannels.model.Channel;

import java.time.LocalDateTime;

@Mapper
public interface ChannelMapper {

    @Mapping(target = "author.id", source = "authorId")
    Channel fromRequest(Long authorId, ChannelRequest request, byte[] avatar);

    @Mapping(target = "author.id", source = "authorId")
    Channel fromRequest(Long id, Long authorId, LocalDateTime createdAt, ChannelRequest request, byte[] avatar);

    @Mapping(target = "authorId", source = "channel.author.id")
    ChannelResponse toResponse(Channel channel);

    ChannelFilterResponse toFilterInfoResponse(Channel channel, Integer subscribersCount);

    ChannelDetailedInformationResponse toDetailedInformationResponse(Channel channel, Integer subscribersCount);

}
