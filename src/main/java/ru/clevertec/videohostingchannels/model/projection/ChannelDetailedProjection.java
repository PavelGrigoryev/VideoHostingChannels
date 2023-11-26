package ru.clevertec.videohostingchannels.model.projection;

import ru.clevertec.videohostingchannels.model.User;

import java.time.LocalDateTime;

public interface ChannelDetailedProjection {

    Long getId();

    String getName();

    String getShortDescription();

    User getAuthor();

    Integer getSubscribersCount();

    LocalDateTime getCreatedAt();

    String getMainLanguage();

    byte[] getAvatar();

    String getCategory();

}
