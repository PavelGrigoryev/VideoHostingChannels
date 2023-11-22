package ru.clevertec.videohostingchannels.dto.subscription;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record SubscriptionRequest(Long userId,
                                  Long channelId) {
}
