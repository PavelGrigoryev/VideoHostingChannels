package ru.clevertec.videohostingchannels.model.listener;

import jakarta.persistence.PrePersist;
import ru.clevertec.videohostingchannels.model.Channel;

import java.time.LocalDateTime;

public class ChannelCreatedAtListener {

    @PrePersist
    public void prePersist(Channel channel) {
        channel.setCreatedAt(LocalDateTime.now());
    }

}
