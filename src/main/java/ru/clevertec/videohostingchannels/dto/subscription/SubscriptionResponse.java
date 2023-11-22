package ru.clevertec.videohostingchannels.dto.subscription;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record SubscriptionResponse(String userNickname,
                                   String channelName,
                                   SubscriptionStatus subscriptionStatus) {
}
