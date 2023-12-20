package ru.clevertec.videohostingchannels.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.clevertec.videohostingchannels.model.Subscription;
import ru.clevertec.videohostingchannels.model.SubscriptionId;

import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscription, SubscriptionId> {

    @EntityGraph(attributePaths = {"user", "channel"})
    Optional<Subscription> findByUserIdAndChannelId(Long userId, Long channelId);

}
