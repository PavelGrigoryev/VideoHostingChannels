package ru.clevertec.videohostingchannels.service;

import ru.clevertec.videohostingchannels.dto.channel.ChannelRequest;
import ru.clevertec.videohostingchannels.dto.channel.ChannelResponse;

public interface ChannelService {

    ChannelResponse saveByAuthorId(Long authorId, ChannelRequest request);

    ChannelResponse updateById(Long id, ChannelRequest request);

}
