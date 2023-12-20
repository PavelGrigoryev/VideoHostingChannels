package ru.clevertec.videohostingchannels.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.clevertec.videohostingchannels.model.Channel;
import ru.clevertec.videohostingchannels.model.projection.ChannelFilterProjection;

import java.util.Optional;

public interface ChannelRepository extends JpaRepository<Channel, Long> {

    @Query("""
            SELECT c.id AS id,
            c.name AS name,
            COUNT(u.id) AS subscribersCount,
            c.mainLanguage AS mainLanguage,
            c.avatar AS avatar,
            c.category AS category
            FROM Channel c
            LEFT JOIN Subscription s ON c.id = s.channel.id
            LEFT JOIN User u ON s.user.id = u.id
            WHERE (:name IS NULL OR c.name LIKE %:name%)
            AND (:language IS NULL OR c.mainLanguage = :language)
            AND (:category IS NULL OR c.category LIKE %:category%)
            GROUP BY c.id
            """)
    Page<ChannelFilterProjection> findAllByFilter(String name, String language, String category, Pageable pageable);

    @EntityGraph(attributePaths = {"author"})
    Optional<Channel> findWithAuthorById(Long id);

    @EntityGraph(attributePaths = {"author", "subscriptions"})
    Optional<Channel> findWithAuthorAndSubscriptionsById(Long id);

    @Modifying
    @Query("""
            UPDATE Channel c SET c.avatar = :avatar
            WHERE c.id = :id
            """)
    void updateAvatarById(Long id, byte[] avatar);

}
