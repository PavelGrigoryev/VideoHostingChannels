package ru.clevertec.videohostingchannels.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.clevertec.videohostingchannels.model.Channel;

import java.util.List;
import java.util.Optional;

public interface ChannelRepository extends JpaRepository<Channel, Long> {

    @Query("""
            SELECT c FROM Channel c
            WHERE (:name IS NULL OR c.name LIKE %:name%)
            AND (:language IS NULL OR c.mainLanguage = :language)
            AND (:category IS NULL OR c.category LIKE %:category%)
            """)
    List<Channel> findAllByFilter(String name, String language, String category, Pageable pageable);

    @Query("""
            SELECT COUNT(u.id) FROM Channel c
            JOIN Subscription s ON c.id = s.channel.id
            JOIN User u ON s.user.id = u.id
            WHERE c.id = :id
            """)
    Integer findSubscribersCountById(Long id);

    @EntityGraph(attributePaths = {"author"})
    Optional<Channel> findDetailedInformationById(Long id);

    @Modifying
    @Query("""
            UPDATE Channel c SET c.avatar = :avatar
            WHERE c.id = :id
            """)
    void updateAvatarById(Long id, byte[] avatar);

}
