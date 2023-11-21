package ru.clevertec.videohostingchannels.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.clevertec.videohostingchannels.model.Channel;

public interface ChannelRepository extends JpaRepository<Channel, Long> {
}
