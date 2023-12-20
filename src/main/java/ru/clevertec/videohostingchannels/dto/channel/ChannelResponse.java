package ru.clevertec.videohostingchannels.dto.channel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.LocalDateTime;
import java.util.Arrays;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ChannelResponse(Long id,
                              String name,
                              String shortDescription,
                              Long authorId,

                              @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
                              LocalDateTime createdAt,

                              String mainLanguage,
                              byte[] avatar,
                              String category) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChannelResponse that = (ChannelResponse) o;
        return Arrays.equals(avatar, that.avatar);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(avatar);
    }

    @Override
    public String toString() {
        return "ChannelResponse{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", shortDescription='" + shortDescription + '\'' +
               ", authorId=" + authorId +
               ", createdAt=" + createdAt +
               ", mainLanguage='" + mainLanguage + '\'' +
               ", avatar=" + Arrays.toString(avatar) +
               ", category='" + category + '\'' +
               '}';
    }

}
