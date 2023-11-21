package ru.clevertec.videohostingchannels.dto.channel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.LocalDateTime;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ChannelResponse(Long id,
                              String name,
                              String shortDescription,
                              Long authorId,

                              @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
                              LocalDateTime createdAt,

                              String mainLanguage,
                              String avatar,
                              String category) {
}
