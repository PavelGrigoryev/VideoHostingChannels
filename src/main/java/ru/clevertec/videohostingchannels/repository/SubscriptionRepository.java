package ru.clevertec.videohostingchannels.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.clevertec.videohostingchannels.model.Subscription;
import ru.clevertec.videohostingchannels.model.SubscriptionId;

public interface SubscriptionRepository extends JpaRepository<Subscription, SubscriptionId> {
}
