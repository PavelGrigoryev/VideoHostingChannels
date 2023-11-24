package ru.clevertec.videohostingchannels.listener;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.videohostingchannels.model.Channel;
import ru.clevertec.videohostingchannels.repository.ChannelRepository;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ChannelAvatarUpdater {

    private final ChannelRepository channelRepository;

    @SneakyThrows
    @Transactional
    @EventListener(ApplicationReadyEvent.class)
    public void updateAvatarsIfNeeded() {
        Optional<Channel> channel = channelRepository.findById(1L);
        if (channel.isPresent() && Objects.isNull(channel.get().getAvatar())) {
            List<String> files = List.of
                    ("arnold", "banana", "cat", "child", "duke_nukem", "kenny", "orc", "spunch_bob", "undead", "vin");
            long i = 1L;
            for (String file : files) {
                channelRepository.updateAvatarById(i++,
                        Files.readAllBytes(Paths.get("src/main/resources/avatars/%s%s".formatted(file, ".jpg"))));
            }
        }
    }

}
