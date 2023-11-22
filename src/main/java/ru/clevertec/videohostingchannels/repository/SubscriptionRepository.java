package ru.clevertec.videohostingchannels.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.clevertec.videohostingchannels.model.Subscription;
import ru.clevertec.videohostingchannels.model.SubscriptionId;

import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscription, SubscriptionId> {

    @Query("""
            SELECT s FROM Subscription s
            JOIN FETCH s.user u
            JOIN FETCH s.channel c
            WHERE u.id = :userId AND c.id = :channelId
            """)
    Optional<Subscription> findByIdWithUserAndChannel(Long userId, Long channelId);

}
