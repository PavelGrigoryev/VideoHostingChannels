package ru.clevertec.videohostingchannels.service;

import org.springframework.data.domain.Pageable;
import ru.clevertec.videohostingchannels.dto.channel.ChannelDetailedInformationResponse;
import ru.clevertec.videohostingchannels.dto.channel.ChannelFilterResponse;
import ru.clevertec.videohostingchannels.dto.channel.ChannelRequest;
import ru.clevertec.videohostingchannels.dto.channel.ChannelResponse;

import java.util.List;

public interface ChannelService {

    ChannelResponse saveByAuthorId(Long authorId, ChannelRequest request);

    ChannelResponse updateById(Long id, ChannelRequest request);

    List<ChannelFilterResponse> findAllByFilter(String name, String language, String category, Pageable pageable);

    ChannelDetailedInformationResponse findDetailedInformationById(Long id);

}
