package ru.clevertec.videohostingchannels.model.projection;

public interface ChannelFilterProjection {

    Long getId();

    String getName();

    Integer getSubscribersCount();

    String getMainLanguage();

    byte[] getAvatar();

    String getCategory();

}
