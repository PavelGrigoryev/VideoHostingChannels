package ru.clevertec.videohostingchannels.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.clevertec.videohostingchannels.model.Channel;
import ru.clevertec.videohostingchannels.model.projection.ChannelDetailedProjection;
import ru.clevertec.videohostingchannels.model.projection.ChannelFilterProjection;

import java.util.List;
import java.util.Optional;

public interface ChannelRepository extends JpaRepository<Channel, Long> {

    @Query("""
            SELECT c.name AS name,
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
    List<ChannelFilterProjection> findAllByFilter(String name, String language, String category, Pageable pageable);

    @EntityGraph(attributePaths = {"author"})
    Optional<Channel> findWithAuthorById(Long id);

    @Query("""
            SELECT c.id AS id,
            c.name AS name,
            c.shortDescription AS shortDescription,
            a AS author,
            COUNT(u.id) AS subscribersCount,
            c.createdAt AS createdAt,
            c.mainLanguage AS mainLanguage,
            c.avatar AS avatar,
            c.category AS category
            FROM Channel c
            JOIN User a ON c.author.id = a.id
            LEFT JOIN Subscription s ON c.id = s.channel.id
            LEFT JOIN User u ON s.user.id = u.id
            WHERE c.id = :id
            GROUP BY c.id, a.id
            """)
    Optional<ChannelDetailedProjection> findDetailedInformationById(Long id);

    @Modifying
    @Query("""
            UPDATE Channel c SET c.avatar = :avatar
            WHERE c.id = :id
            """)
    void updateAvatarById(Long id, byte[] avatar);

}
