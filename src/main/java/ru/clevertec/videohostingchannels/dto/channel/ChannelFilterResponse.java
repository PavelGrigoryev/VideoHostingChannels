package ru.clevertec.videohostingchannels.dto.channel;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.Arrays;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ChannelFilterResponse(Long id,
                                    String name,
                                    Integer subscribersCount,
                                    String mainLanguage,
                                    byte[] avatar,
                                    String category) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChannelFilterResponse that = (ChannelFilterResponse) o;
        return Arrays.equals(avatar, that.avatar);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(avatar);
    }

    @Override
    public String toString() {
        return "ChannelFilterResponse{" +
               "name='" + name + '\'' +
               ", subscribersCount=" + subscribersCount +
               ", mainLanguage='" + mainLanguage + '\'' +
               ", avatar=" + Arrays.toString(avatar) +
               ", category='" + category + '\'' +
               '}';
    }

}
