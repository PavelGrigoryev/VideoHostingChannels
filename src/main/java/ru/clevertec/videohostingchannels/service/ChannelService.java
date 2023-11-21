package ru.clevertec.videohostingchannels.service;

import ru.clevertec.videohostingchannels.dto.channel.ChannelRequest;
import ru.clevertec.videohostingchannels.dto.channel.ChannelResponse;

public interface ChannelService {

    ChannelResponse save(ChannelRequest request);

}
