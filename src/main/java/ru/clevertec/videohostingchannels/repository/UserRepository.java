package ru.clevertec.videohostingchannels.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.clevertec.videohostingchannels.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @EntityGraph(attributePaths = {"subscriptions.channel"})
    Optional<User> findWithSubscriptionsChannelById(Long id);

}
