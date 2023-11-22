package ru.clevertec.videohostingchannels.dto.channel;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ChannelFilterResponse(String name,
                                    Integer subscribersCount,
                                    String mainLanguage,
                                    String avatar,
                                    String category) {
}
