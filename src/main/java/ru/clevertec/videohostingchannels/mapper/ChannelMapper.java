package ru.clevertec.videohostingchannels.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.clevertec.videohostingchannels.dto.channel.ChannelRequest;
import ru.clevertec.videohostingchannels.dto.channel.ChannelResponse;
import ru.clevertec.videohostingchannels.model.Channel;
import ru.clevertec.videohostingchannels.model.User;

@Mapper(uses = UserMapper.class)
public interface ChannelMapper {

    @Mapping(target = "author.id", source = "request.authorId")
    Channel fromRequest(ChannelRequest request);

    @Mapping(target = "id", source = "channel.id")
    @Mapping(target = "name", source = "channel.name")
    @Mapping(target = "author", source = "user")
    ChannelResponse toResponse(Channel channel, User user);

}
