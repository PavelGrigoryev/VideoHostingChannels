package ru.clevertec.videohostingchannels.mapper;

import org.mapstruct.Mapper;
import ru.clevertec.videohostingchannels.dto.channel.ChannelRequest;
import ru.clevertec.videohostingchannels.dto.channel.ChannelResponse;
import ru.clevertec.videohostingchannels.model.Channel;

@Mapper(uses = UserMapper.class)
public interface ChannelMapper {

    Channel fromRequest(ChannelRequest request);

    ChannelResponse toResponse(Channel channel);

}
