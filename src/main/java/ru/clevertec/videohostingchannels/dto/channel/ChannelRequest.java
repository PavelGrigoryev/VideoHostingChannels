package ru.clevertec.videohostingchannels.dto.channel;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ChannelRequest(String name,
                             String shortDescription,
                             Long authorId,
                             String mainLanguage,
                             String avatar,
                             String category) {
}
