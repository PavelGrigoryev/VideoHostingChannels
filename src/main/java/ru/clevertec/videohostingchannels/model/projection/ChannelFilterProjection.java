package ru.clevertec.videohostingchannels.model.projection;

public interface ChannelFilterProjection {

    String getName();

    Integer getSubscribersCount();

    String getMainLanguage();

    byte[] getAvatar();

    String getCategory();

}
