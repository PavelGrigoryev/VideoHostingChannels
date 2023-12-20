package ru.clevertec.videohostingchannels.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import ru.clevertec.videohostingchannels.dto.channel.ChannelDetailedInformationResponse;
import ru.clevertec.videohostingchannels.dto.channel.ChannelFilterResponse;
import ru.clevertec.videohostingchannels.dto.channel.ChannelRequest;
import ru.clevertec.videohostingchannels.dto.channel.ChannelResponse;

public interface ChannelService {

    ChannelResponse saveByAuthorId(Long authorId, ChannelRequest request, MultipartFile file);

    ChannelResponse updateById(Long id, ChannelRequest request, MultipartFile file);

    Page<ChannelFilterResponse> findAllByFilter(String name, String language, String category, Pageable pageable);

    ChannelDetailedInformationResponse findDetailedInformationById(Long id);

    byte[] downloadChannelAvatarById(Long id);

}
